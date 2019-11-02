package com.example.newsinfluence.activities

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.newsinfluence.R
import com.example.newsinfluence.fragments.BaseFragment
import com.example.newsinfluence.helpers.Constants
import com.example.newsinfluence.interfaces.OnActivityAlert
import com.example.newsinfluence.interfaces.OnActivityFragmentCommunication
import com.example.newsinfluence.interfaces.OnRequestAction
import com.example.newsinfluence.widgets.LoadingDialog

abstract class BaseActivity : AppCompatActivity(), OnActivityAlert, OnActivityFragmentCommunication {

    protected var mProgressDialog: LoadingDialog? = null

    protected val EMPTY_STRING = ""

    protected var mFragmentManager: FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
        setupViews()

        mFragmentManager = this.supportFragmentManager
    }

    override fun onBackPressed() {
        val fragmentsCount = mFragmentManager?.backStackEntryCount ?: 0
        val TOP_TAG = mFragmentManager?.getBackStackEntryAt(fragmentsCount.minus(1))?.name

        when (TOP_TAG) {
            Constants.FragmentTags.TAG_FRAGMENT_HOME -> {
                finish()
                return
            }
        }

        if (fragmentsCount <= 1) {
            finish()
            return
        }

        super.onBackPressed()
    }

    /**
     * Function used to initialize the activity_main's views
     */
    private fun initViews() {
        mProgressDialog = LoadingDialog(this)
    }

    private fun setupViews() {
        val pd = mProgressDialog
        if (pd != null) {
            pd.requestWindowFeature(Window.FEATURE_NO_TITLE)
            pd.setContentView(R.layout.item_alert_progress)
            pd.setCancelable(false)

            pd.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    /*************************************************************
     * Mark: ALERT
     *************************************************************/

    /**
     * Function called to show the progress spinner - [.mProgressDialog]
     */
    override fun showProgressDialog() {
        val pd = mProgressDialog
        if (pd != null && !pd.isShowing) {
            runOnUiThread { pd.show() }
        }
    }

    /**
     * Function called to hide the progress spinner - [.mProgressDialog]
     */
    override fun hideProgressDialog() {
        val pd = mProgressDialog
        if (pd != null && pd.isShowing) {
            runOnUiThread { pd.dismiss() }
        }
    }

    override fun showAlert(message: String): AlertDialog {
        return if (message == Constants.ApiStatus.ERROR_NULL) {
            showAlert(
                resources.getString(R.string.alert_error_null),
                resources.getString(R.string.alert_ok),
                null
            )
        } else {
//            showAlert(message,
//                    false,
//                    false,
//                    EMPTY_STRING,
//                    EMPTY_STRING,
//                    null)
            showAlert(
                message,
                getString(R.string.alert_ok),
                null
            )
        }
    }

    override fun showAlert(
        message: String,
        negativeButtonText: String,
        mRequestAction: OnRequestAction?,
        isCancelable: Boolean
    ): AlertDialog {

        return showAlert(
            message,
            false,
            true,
            EMPTY_STRING,
            negativeButtonText,
            mRequestAction,
            isCancelable
        )
    }

    override fun showAlert(
        message: String,
        positiveButtonText: String,
        negativeButtonText: String,
        mRequestAction: OnRequestAction?,
        isCancelable: Boolean
    ): AlertDialog {

        return showAlert(
            message,
            true,
            true,
            positiveButtonText,
            negativeButtonText,
            mRequestAction,
            isCancelable
        )
    }

    private fun showAlert(
        message: String, isPositiveButton: Boolean, isNegativeButton: Boolean,
        positiveButtonText: String, negativeButtonText: String,
        mRequestAction: OnRequestAction?, isCancelable: Boolean
    ): AlertDialog {
        hideProgressDialog()

        val b = AlertDialog.Builder(this)
            .setMessage(message)
            .setCancelable(true)

        if (isPositiveButton) {
            b.setPositiveButton(positiveButtonText) { _, _ ->
                mRequestAction?.requestPositiveAction()
            }
        }

        if (isNegativeButton) {
            b.setNegativeButton(negativeButtonText) { _, _ ->
                mRequestAction?.requestNegativeAction()
            }
            b.setCancelable(isCancelable)
        }

        val mAlert = b.create()
        mAlert.show()

        return mAlert
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /*************************************************************
     * Mark: OnActivityFragmentCommunication
     */
    private enum class FragmentActions {
        ADD,
        REPLACE,
        REMOVE
    }

    override fun onAddFragment(TAG: String, bundle: Bundle?) {
        onCreateFragmentAction(TAG, FragmentActions.ADD, bundle)
    }

    override fun onReplaceFragment(TAG: String, bundle: Bundle?) {
        onCreateFragmentAction(TAG, FragmentActions.REPLACE, bundle)
    }

    override fun onRemoveFragment(TAG: String) {
        onCreateFragmentAction(TAG, FragmentActions.REMOVE)
    }

    override fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    /*************************************************************
     * Mark: Fragment actions
     *************************************************************/

    protected abstract fun getFragmentContainer(): Int?

    private fun onAddFragment(fragment: BaseFragment, TAG: String) {
        val fm = mFragmentManager ?: return
        val fragmentContainer = getFragmentContainer() ?: return

        val mFragmentTransaction = fm.beginTransaction()

        mFragmentTransaction
//                .setCustomAnimations(getFragmentTransitionAnimRightToLeft()[0],
//                        getFragmentTransitionAnimRightToLeft()[1],
//                        getFragmentTransitionAnimLeftToRight()[0],
//                        getFragmentTransitionAnimLeftToRight()[1])
            .replace(
                fragmentContainer,
                fragment,
                TAG
            )
            .addToBackStack(TAG)
            .commit()
    }

    private fun onReplaceFragment(fragment: BaseFragment, TAG: String) {
        val fm = mFragmentManager ?: return
        val fragmentContainer = getFragmentContainer() ?: return

        val mFragmentTransaction = fm.beginTransaction()

        mFragmentTransaction
            .setCustomAnimations(
                getFragmentTransitionAnimRightToLeft()[0],
                getFragmentTransitionAnimRightToLeft()[1],
                getFragmentTransitionAnimLeftToRight()[0],
                getFragmentTransitionAnimLeftToRight()[1]
            )
            .replace(
                fragmentContainer,
                fragment,
                TAG
            )
        mFragmentTransaction.addToBackStack(TAG)

        mFragmentTransaction.commit()
    }

    private fun onRemoveFragment(fragment: BaseFragment) {
        val fm = mFragmentManager ?: return

        val mFragmentTransaction = fm.beginTransaction()

        mFragmentTransaction
            .remove(fragment)

        mFragmentTransaction.commit()
    }

    private fun onCreateFragmentAction(
        TAG: String,
        fragmentAction: FragmentActions,
        bundle: Bundle? = null
    ) {
        val fragment: BaseFragment?

        when (fragmentAction) {
            FragmentActions.ADD -> {
                fragment = getFragmentByTag(TAG) ?: return
                fragment.arguments = bundle
                onAddFragment(fragment, TAG)
            }

            FragmentActions.REPLACE -> {
                fragment = getFragmentByTag(TAG) ?: return
                fragment.arguments = bundle
                onReplaceFragment(fragment, TAG)
            }

            FragmentActions.REMOVE -> {
                fragment = getFragmentByTag(TAG) ?: return
                onRemoveFragment(fragment)
            }
        }
    }

    protected abstract fun getFragmentByTag(TAG: String): BaseFragment?

    private fun getFragmentTransitionAnimRightToLeft(): IntArray =
        intArrayOf(R.anim.enter_from_right, R.anim.exit_to_left)

    private fun getFragmentTransitionAnimLeftToRight(): IntArray =
        intArrayOf(R.anim.enter_from_left, R.anim.exit_to_right)

    /*************************************************************
     * Mark: Keyboard
     *************************************************************/

    fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // check if no view has focus
        currentFocus?.let {
            inputManager.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}