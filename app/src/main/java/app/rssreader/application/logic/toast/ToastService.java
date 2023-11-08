package app.rssreader.application.logic.toast;

import android.content.Context;
import android.widget.Toast;

public class ToastService {
    private static Toast lastToast;

    public static void showMessage(Context context, String message) {
        if (lastToast != null) {
            lastToast.cancel();
        }

        lastToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        lastToast.show();
    }
}
