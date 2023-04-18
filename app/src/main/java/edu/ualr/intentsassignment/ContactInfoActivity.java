package edu.ualr.intentsassignment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.ualr.intentsassignment.model.Contact;

public class ContactInfoActivity extends AppCompatActivity {
    public Button call_btn;
    public Button text_btn;
    public Button map_btn;
    public Button email_btn;
    public Button web_btn;

    public String phone;
    public String email;
    public String address;
    public String website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_info);

        readContactForm();

        call_btn=findViewById(R.id.call_chip);
        text_btn=findViewById(R.id.text_chip);
        email_btn=findViewById(R.id.email_chip);
        map_btn=findViewById(R.id.map_chip);
        web_btn=findViewById(R.id.web_chip);

        call_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onCallButtonClick(v);
            }
        });
        text_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onTextButtonClick(v);
            }
        });
        email_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onEmailButtonClick(v);
            }
        });
        map_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onMapButtonClick(v);
            }
        });
        web_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onWebButtonClick(v);
            }
        });
        }

    // TODO 03. DONE Create a new layout file to define the GUI elements of the ContactInfoActivity.
    // TODO 04.DONE Define the basic skeleton of the ContactInfoActivity. Inflate the layout defined in the first step to display the GUI elements on screen.
    // TODO 07. DONE Create a new method that reads the contact info coming from ContactFormActivity and use it to populate the several TextView elements in the layout.

    // TODO 08. Create a new method that invokes a Phone Dialer app, using as parameter the phone number included in the contact info received from ContactFormActivity in the previous step

    // TODO 09. Create a new method that invokes a Messages app, using as parameter the phone number included in the contact info received from ContactFormActivity in the 7th step
    // TODO 10. Create a new method that invokes a Maps app, using as parameter the address included in the contact info received from ContactFormActivity in the 7th step
    // TODO 11. Create a new method that invokes an Email app, using as parameter the email address included in the contact info received from ContactFormActivity in the 7th step
    // TODO 12. Create a new method that invokes an Web Browser app, using as parameter the web url included in the contact info received from ContactFormActivity in the 7th step
    void readContactForm(){
        Contact contact = getIntent().getParcelableExtra(ContactFormActivity.EXTRA_CONTACT);
        phone = contact.getPhoneNumber();
        email = contact.getEmailAddress();
        address=contact.getAddress();
        website=contact.getWebsite();

        ((TextView)findViewById(R.id.contact_name)).setText(contact.getFullName());
        ((TextView)findViewById(R.id.phone_textView)).setText(phone);
        ((TextView)findViewById(R.id.map_textView)).setText(address);
        ((TextView)findViewById(R.id.email_textView)).setText(email);
        ((TextView)findViewById(R.id.web_textView)).setText(website);
    }
    public void onCallButtonClick(View v){

        String PhoneURI = "tel:" + phone;
        Uri number = Uri.parse(PhoneURI);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);

        startActivity(callIntent);
    }

    public void onTextButtonClick(View v) {
        String textURI = "smsto:" + phone;
        Uri number = Uri.parse(textURI);
        Intent textIntent = new Intent(Intent.ACTION_SEND);
        textIntent.setData(number);
        if (textIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(textIntent);
        }
    }

    public void onEmailButtonClick(View v) {
        String emailURI ="mailto:" + email;
        Uri emailAd = Uri.parse(emailURI) ;
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(emailAd);
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }

    public void onMapButtonClick(View v){
        String mapURI = address.replace(" ", "+");
        Uri map= Uri.parse("google.navigation:q=" + mapURI);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, map);

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }
    public void onWebButtonClick(View v){
        String webURI = website;
        if (!website.startsWith("http://") && !website.startsWith("https://")){
            webURI="http://"+website;
        }
        Uri link = Uri.parse(webURI);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, link);
        if (webIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(webIntent);
        }
    }
}


