package cbudtz.uge02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GalgeLeg extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    Galgelogik gctrl;
    Button b;
    EditText letterBox;
    TextView wordBox;
    TextView infoBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galge_leg);
        gctrl=new Galgelogik();
        gctrl.nulstil();
        b = (Button) findViewById(R.id.galgle_buttongæt);
        b.setOnClickListener(this);
        letterBox = (EditText) findViewById(R.id.galge_letterBox);
        letterBox.addTextChangedListener(this);
        wordBox = (TextView) findViewById(R.id.galge_text);
        infoBox = (TextView) findViewById(R.id.galge_infobox);
        letterBox.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_galge_leg, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String letter = letterBox.getText().toString();
        gctrl.gætBogstav(letter.toLowerCase());
        String ord = gctrl.getSynligtOrd();
        wordBox.setText(ord);
        infoBox.setText(gctrl.erSidsteBogstavKorrekt() ? "Rigtigt bogstav: " + letter +" , prøvet:" + gctrl.getBrugteBogstaver().toString(): "Forkert Bogstav: " + letter+" , prøvet:" + gctrl.getBrugteBogstaver().toString());
        if (gctrl.erSpilletVundet()){
            infoBox.setText("Tillykke Du vandt, Ordet var " + ord);
            gctrl.nulstil();
            wordBox.setText(gctrl.getSynligtOrd());

        }
        if (gctrl.erSpilletTabt()){infoBox.setText("Desværre - prøv igen :(");gctrl.nulstil();}
        letterBox.setText("");


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(letterBox.getText().toString().length()==1)     onClick(null);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
