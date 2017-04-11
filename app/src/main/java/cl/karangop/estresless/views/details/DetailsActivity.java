package cl.karangop.estresless.views.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import cl.karangop.estresless.R;
import cl.karangop.estresless.models.Pending;

public class DetailsActivity extends AppCompatActivity {

    private Pending pending;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        long id= getIntent().getLongExtra("id",0); //default 0 rescatamos valores
        pending= Pending.findById(Pending.class,id);

        //Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();

        //Para getIntent: debo tener en cuenta la clave y el tipo que estoy pasando

        //Pending pending= (Pending) getIntent().getSerializableExtra("pending"); //cast objeto 1era forma
        //pending= (Pending) getIntent().getSerializableExtra(PendingListFragment.PENDING); //2da forma

        getSupportActionBar().setTitle(pending.getName()); //o setTitle()

    }

    //Cuando la actividad se inicia, después de ya iniciada
    @Override
    protected void onResume() {
        super.onResume();
//        Log.d("LIFECYCLE", "OnResume");
        editText= (EditText) findViewById(R.id.descriptionEt);
        String description= pending.getDescription();
        if(description != null){
            editText.setText(description);
        }
    }

    //cuando la app se minimiza o cambia de activity, pero la información se guarda
    @Override
    protected void onPause() {
        super.onPause();

        String description= editText.getText().toString();
        pending.setDescription(description);
        pending.save();

    }
}
