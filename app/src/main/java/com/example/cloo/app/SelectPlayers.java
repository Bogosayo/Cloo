package com.example.cloo.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


public class SelectPlayers extends Activity {

    int numPlayers = 0;
    boolean fromSelectNumPlayers = false;
    PlayerData[] playerData = new PlayerData[6];
    public void instantiatePlayers(){
        for (int i = 0; i < 6; i++){
            PlayerData p = new PlayerData();
            playerData[i] = p;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_players);
        setTitle("Number of Players?");
        instantiatePlayers();
        updateActionBar(0);

        ActionBar actionBar = getActionBar();
        actionBar.setIcon(R.drawable.tinyface);


        /** Character Select Spinner */

        Spinner spinner = (Spinner) findViewById(R.id.characterSelectSpinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.character_names_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter1);

        /**TO-DO: Make character select spinner listener*/
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner spinner = (Spinner) findViewById(R.id.characterSelectSpinner);
                String selectedCharacter = spinner.getSelectedItem().toString();

                ActionBar actionBar = getActionBar();
                int selectPlayer = actionBar.getSelectedNavigationIndex();

                Switch humanity = (Switch) findViewById(R.id.humanitySwitch);

                RelativeLayout playerOptions = (RelativeLayout) findViewById(R.id.PlayerOptions);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Toast.makeText(getBaseContext(), "Choose the number of players", Toast.LENGTH_SHORT).show();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.select_players, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateActionBar(int arrayNum){
        /** Create an array adapter to populate dropdownlist */
        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.players0, android.R.layout.simple_spinner_dropdown_item);
        switch (arrayNum){
            case 0:
                break;
            case 2:
                mSpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.players2, android.R.layout.simple_spinner_dropdown_item);
                numPlayers = 2;
                break;
            case 3:
                mSpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.players3, android.R.layout.simple_spinner_dropdown_item);
                numPlayers = 3;
                break;
            case 4:
                mSpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.players4, android.R.layout.simple_spinner_dropdown_item);
                numPlayers = 4;
                break;
            case 5:
                mSpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.players5, android.R.layout.simple_spinner_dropdown_item);
                numPlayers = 5;
                break;
            case 6:
                mSpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.players6, android.R.layout.simple_spinner_dropdown_item);
                numPlayers = 6;
                break;
        }

        /** Enabling dropdown list navigation for the action bar */
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        /** Defining Navigation listener */
        ActionBar.OnNavigationListener navigationListener = new ActionBar.OnNavigationListener() {

            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {

                if (fromSelectNumPlayers){
                    editPlayer((itemPosition + 50));
                }
                else {
                    editPlayer(itemPosition);
                }
                return false;
            }
        };

        /** Setting dropdown items and item navigation listener for the actionbar */
        getActionBar().setListNavigationCallbacks(mSpinnerAdapter, navigationListener);
        return;
    }




    public void explainNumbers(View view){
        Toast.makeText(getBaseContext(), "At the top: select player to edit", Toast.LENGTH_SHORT).show();
    }
    public void explainHumanity(View view){
        Toast.makeText(getBaseContext(), "Computer, or Human Player?", Toast.LENGTH_SHORT).show();
    }
    public void explainCharacterSelect(View view){
        Toast.makeText(getBaseContext(), "Choose your character", Toast.LENGTH_SHORT).show();
    }


    public void selectNumPlayers(View view){
        int numPlayers = Integer.parseInt(view.getTag().toString());
        setTitle(numPlayers + " Players");
        fromSelectNumPlayers = true;
        updateActionBar(numPlayers);
        saveActiveStatus();
        TableLayout pickNumPlayers = (TableLayout) findViewById((R.id.NumPlayersLayout));
        RelativeLayout playerOptions = (RelativeLayout) findViewById(R.id.PlayerOptions);
        pickNumPlayers.setVisibility(View.GONE);
        playerOptions.setVisibility(View.VISIBLE);





    }

    int lastItem = 0;
    public void editPlayer(int playerNum){
        TableLayout pickNumPlayers = (TableLayout) findViewById((R.id.NumPlayersLayout));
        RelativeLayout playerOptions = (RelativeLayout) findViewById(R.id.PlayerOptions);
        switch (playerNum){
            case 0:
                Toast.makeText(getBaseContext(), "Choose number of players", Toast.LENGTH_SHORT).show();
                playerOptions.setVisibility(View.GONE);
                pickNumPlayers.setVisibility(View.VISIBLE);
                break;
            case 1:case 2:case 3:case 4:case 5:case 6:
                Toast.makeText(getBaseContext(), "Editing Player " + (playerNum), Toast.LENGTH_SHORT).show();
                pickNumPlayers.setVisibility(View.GONE);
                playerOptions.setVisibility(View.VISIBLE);
                loadPlayerData(playerNum - 1);
                break;
            case 50:
                Toast.makeText(getBaseContext(), "Editing Player 1", Toast.LENGTH_SHORT).show();

                ActionBar actionBar = getActionBar();
                actionBar.setSelectedNavigationItem(1);

                pickNumPlayers.setVisibility(View.GONE);
                playerOptions.setVisibility(View.VISIBLE);
                loadPlayerData(1);
                break;
            case 51:case 52:case 53:case 54:case 55:case 56:
                Toast.makeText(getBaseContext(), "Editing Player " + (playerNum - 50), Toast.LENGTH_SHORT).show();
                pickNumPlayers.setVisibility(View.GONE);
                playerOptions.setVisibility(View.VISIBLE);
                loadPlayerData((playerNum - 51));
                break;

            default:
        }
        fromSelectNumPlayers = false;
    }
    /**TO-DO: add background images for human and robot */
    public void humanitySwitchJobs(View view){
        allowCharacterSelect();
        saveHumanity();
    }
    public void allowCharacterSelect(){
        Switch humanitySwitch = (Switch) findViewById(R.id.humanitySwitch);
        Spinner characterSelectSpinner = (Spinner) findViewById(R.id.characterSelectSpinner);
        Button infoButton = (Button) findViewById(R.id.characterSelectInfoButton);
        if (humanitySwitch.isChecked()){
            characterSelectSpinner.setVisibility(View.VISIBLE);
            infoButton.setVisibility(View.VISIBLE);
        }
        else{
            characterSelectSpinner.setVisibility(View.GONE);
            infoButton.setVisibility(View.GONE);
        }
    }
    public void saveHumanity(){
        ActionBar actionBar = getActionBar();
        int selectedPlayer = (actionBar.getSelectedNavigationIndex() - 1);

        Switch humanity = (Switch) findViewById(R.id.humanitySwitch);
        playerData[selectedPlayer].setHumanity(humanity.isChecked());

    }

    public void saveActiveStatus(){
        ActionBar actionBar = getActionBar();
        int numPlayers = (actionBar.getNavigationItemCount() - 1);
        //deactivate nonparticipating players
        switch (numPlayers){
            case 2:
                playerData[2].setActiveStatus(false);
            case 3:
                playerData[3].setActiveStatus(false);
            case 4:
                playerData[4].setActiveStatus(false);
            case 5:
                playerData[5].setActiveStatus(false);
                break;
        }
        //reactivate participating players
        switch (numPlayers){
            case 6:
                playerData[5].setActiveStatus(true);
            case 5:
                playerData[4].setActiveStatus(true);
            case 4:
                playerData[3].setActiveStatus(true);
            case 3:
                playerData[2].setActiveStatus(true);
                break;
        }
    }

    public void loadPlayerData(int selectedPlayer){
        Switch humanity = (Switch) findViewById(R.id.humanitySwitch);
        humanity.setChecked(playerData[selectedPlayer].isHuman());
        allowCharacterSelect();
    }

}
