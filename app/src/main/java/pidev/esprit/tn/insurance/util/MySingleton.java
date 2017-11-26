package pidev.esprit.tn.insurance.util;

/**
 * Created by aboud on 25/11/2017.
 */
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class MySingleton {
    private static MySingleton ins;
    private RequestQueue req;
    private  static Context ctx;


    public MySingleton(Context c) {
        ctx = c ;
        req = getrequest();
    }




    public  RequestQueue getrequest(){

        if(req ==null){

            req = Volley.newRequestQueue(ctx.getApplicationContext());
        }

        return req;
    }



    public static synchronized MySingleton getIns(Context c){


        if(ins ==null){


            ins = new MySingleton(c);

        }

        return ins;
    }



    public<T> void  addToRequ(Request<T> request)
    {

        req.add(request);
    }



}
