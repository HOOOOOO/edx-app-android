package org.edx.mobile.http.notifications;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.joanzapata.iconify.Icon;

import org.edx.mobile.base.BaseFragment;
import org.edx.mobile.view.dialog.AlertDialogFragment;

/**
 * A modal dialog notification error message.
 */
public class DialogErrorNotification extends ErrorNotification {
    /**
     * The fragment of a concerned activity.
     */
    @NonNull
    private final BaseFragment baseFragment;

    /**
     * Construct a new instance of the notification.
     *
     * @param baseFragment The fragment of a concerned activity responsible for creation of a
     *                     DialogFragment.
     */
    public DialogErrorNotification(@NonNull final BaseFragment baseFragment) {
        this.baseFragment = baseFragment;
    }

    /**
     * Show the error notification as a modal dialog, according to the provided details.
     *
     * @param errorResId      The resource ID of the error message.
     * @param icon            The error icon. This is currently ignored here.
     * @param actionTextResId The resource ID of the action button text.
     * @param actionListener  The callback to be invoked when the action button is clicked.
     */
    @Override
    public void showError(@StringRes final int errorResId,
                          @Nullable final Icon icon,
                          @StringRes final int actionTextResId,
                          @Nullable final View.OnClickListener actionListener) {
        if (baseFragment.isResumed()) {
            AlertDialogFragment.newInstance(0, errorResId,
                    actionListener == null ? null :
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    actionListener.onClick(((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE));
                                }
                            }
            ).show(baseFragment.getFragmentManager(), null);
        }
    }
}
