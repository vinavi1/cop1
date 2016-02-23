package com.example.android.online2;



        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentTransaction;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.Toast;


public class PrimaryFragment extends Fragment{




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.primary_layout,null);
        Button butt = (Button) v.findViewById(R.id.butt);
        //Put the value
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocialFragment ldf = new SocialFragment();
                Bundle args = new Bundle();
                args.putString("YourKey", "YourValue");
                ldf.setArguments(args);

            //Inflate the fragment
                getFragmentManager().beginTransaction().replace(R.id.containerView, new SocialFragment()).commit();
            }
        });


        return v;

    }

}