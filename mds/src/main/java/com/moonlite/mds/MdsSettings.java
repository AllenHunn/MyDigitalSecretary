package com.moonlite.mds;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.CheckBox;

public class MdsSettings extends ActionBarActivity {
    private ToggleButton toggleAll;
    private ImageButton ibtnGetHelp;
    private EditText editName;
    private EditText codeWord;
    private CheckBox cbRespondToCalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mds_settings);

        toggleAll = (ToggleButton) findViewById(R.id.overallToggleBtn);
        editName = (EditText) findViewById(R.id.editName);
        codeWord = (EditText) findViewById(R.id.editCodeword);
        cbRespondToCalls = (CheckBox) findViewById(R.id.cbRespondToKnownMobile);
        ibtnGetHelp = (ImageButton) findViewById(R.id.ibtnGetHelp);


        toggleAll.setChecked(Settings.getOn(this));
        editName.setText(Settings.getName(this));
        codeWord.setText(Settings.getCodeWord(this));
        cbRespondToCalls.setChecked(Settings.isRespondToCalls(this));
        setRadioButtonDefaults();

        editName.addTextChangedListener(NameTextWatcher);
        codeWord.addTextChangedListener(CodeWordTextWatcher);
    }

    private void setRadioButtonDefaults() {
        if (Settings.isSendAlertToAllContacts(this))
            ((RadioButton)findViewById(R.id.rbRespondToContacts)).setChecked(true);
        else if (Settings.isSendAlertToMemberContacts(this))
            ((RadioButton)findViewById(R.id.rbRespondToFavorites)).setChecked(true);
        else if (Settings.isSendAlertToNonContacts(this))
            ((RadioButton)findViewById(R.id.rbRespondToEveryone)).setChecked(true);

        if (Settings.isAllowAllContactsThrough(this))
            ((RadioButton)findViewById(R.id.rbRingForContacts)).setChecked(true);
        else if (Settings.isAllowSpecialContactsThrough(this))
            ((RadioButton)findViewById(R.id.rbRingForFavorites)).setChecked(true);
        else
            ((RadioButton)findViewById(R.id.rbRingForNoone)).setChecked(true);
    }

    private TextWatcher NameTextWatcher = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        public void onTextChanged(CharSequence s, int start, int before, int end) {


        }
        public synchronized void afterTextChanged(Editable text) {
            Settings.setName(getBaseContext(), text.toString());
        }
    };

    private TextWatcher CodeWordTextWatcher = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        public void onTextChanged(CharSequence s, int start, int before, int end) {


        }
        public synchronized void afterTextChanged(Editable text) {
            Settings.setCodeWord(getBaseContext(), text.toString());
        }
    };

    public void getHelpClick(View v){
        LayoutInflater layoutInflater
                = (LayoutInflater)getBaseContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.help_window, null);
        final PopupWindow popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        Button btnDismiss = (Button)popupView.findViewById(R.id.btnClose);
        btnDismiss.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setOutsideTouchable(false);
        View parentView = (View)v.getParent();
        popupWindow.setHeight(parentView.getHeight());
        popupWindow.setWidth(parentView.getWidth());
        ((TextView)popupView.findViewById(R.id.helpText)).setText(String.format(getResources().getString(R.string.help_text), String.format(getResources().getString(R.string.sms_text), Settings.getName(this), Settings.getCodeWord(this))));
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }

    public void toggleOverall(View v){
        Settings.setOn(this, toggleAll.isChecked());
    }


    public void cbChanged(View v){
        Settings.setRespondToCalls(this, cbRespondToCalls.isChecked());
    }

    public void rgSmsClicked(View v){
        boolean checked = ((RadioButton) v).isChecked();
        switch(v.getId()) {
            case R.id.rbRespondToContacts:
                if (checked)
                {
                    Settings.setSendAlertToAllContacts(this, true);
                    Settings.setSendAlertToNonContacts(this, false);
                    Settings.setSendAlertToMemberContacts(this, false);
                }
                break;
            case R.id.rbRespondToFavorites:
                if (checked)
                {
                    Settings.setSendAlertToAllContacts(this, false);
                    Settings.setSendAlertToNonContacts(this, false);
                    Settings.setSendAlertToMemberContacts(this, true);
                }
                break;
            case R.id.rbRespondToEveryone:
                if (checked)
                {
                    Settings.setSendAlertToAllContacts(this, false);
                    Settings.setSendAlertToNonContacts(this, true);
                    Settings.setSendAlertToMemberContacts(this, false);
                }
                break;
            default:
                break;
        }
    }

    public void rgCallsClicked(View v){
        boolean checked = ((RadioButton) v).isChecked();
        switch(v.getId()) {
            case R.id.rbRingForContacts:
                if (checked)
                {
                    Settings.setAllowAllContactsThrough(this, true);
                    Settings.setAllowSpecialContactsThrough(this, false);
                }
                break;
            case R.id.rbRingForFavorites:
                if (checked)
                {
                    Settings.setAllowAllContactsThrough(this, false);
                    Settings.setAllowSpecialContactsThrough(this, true);
                }
                break;
            case R.id.rbRingForNoone:
                if (checked)
                {
                    Settings.setAllowAllContactsThrough(this, false);
                    Settings.setAllowSpecialContactsThrough(this, false);
                }
                break;
            default:
                break;
        }
    }
}
