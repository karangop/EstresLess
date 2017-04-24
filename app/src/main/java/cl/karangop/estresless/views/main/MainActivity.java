package cl.karangop.estresless.views.main;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import cl.karangop.estresless.R;
import cl.karangop.estresless.models.Pending;

public class MainActivity extends AppCompatActivity implements PendingCallback{

    private PendingListFragment pendingListFragment;
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pendingListFragment= (PendingListFragment) getSupportFragmentManager().findFragmentById(R.id.pendingListFragment);

        //Dog dog= new Dog("firulais");
        //Dog dog= new Dog("pluto");
        /*Dog dog= new Dog("bethoven");
        dog.save();*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                /*Dog dog1= Dog.findById(Dog.class, 1L); //1L 1 long
                Snackbar.make(view, dog1.getName(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                List<Dog> dogs= Dog.listAll(Dog.class);
                if (dogs != null && dogs.size() >0 ){
                    for (Dog dog2:dogs){
                        Log.d("DOGS",dog2.getName());
                    }
                }*/


                final Dialog dialog= new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//sacar title bar
                dialog.setContentView(R.layout.dialog_pending);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                ImageButton saveBtn= (ImageButton) dialog.findViewById(R.id.savePendingBtn);

                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText input= (EditText) dialog.findViewById(R.id.pendingEt);
                        String name= input.getText().toString(); //rescatamos el valor del edit text

                        CreatePending createPending= new CreatePending(MainActivity.this);
                        createPending.validation(name);
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void shareIntent(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "http://");
        startActivity(shareIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            shareIntent();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void created(Pending pending) {
        pendingListFragment.addPending(pending);


    }

    @Override
    public void noName() {
        Toast.makeText(this, "Un nombre por favor", Toast.LENGTH_SHORT).show();

    }
}
