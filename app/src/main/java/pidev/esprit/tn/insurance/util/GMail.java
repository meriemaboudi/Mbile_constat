package pidev.esprit.tn.insurance.util;

import android.util.Log;

/**
 * Created by aboud on 26/11/2017.
 */
public class GMail {

    protected void sendEmail() {

        try {
            GMailSender sender = new GMailSender("hichem.alouis123@gmail.com", "tunis123456");
            sender.sendMail("This is Subject",
                    "This is Body",
                    "hichem.alouis123@gmail.com",
                    "ali.methnani@esprit.tn");
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }



    }
}
