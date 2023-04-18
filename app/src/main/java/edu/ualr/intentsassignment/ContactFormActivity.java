package edu.ualr.intentsassignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import edu.ualr.intentsassignment.model.Contact;

public class ContactFormActivity extends AppCompatActivity {
    public static final String EXTRA_CONTACT = "ContactData";

    public Button save_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_form);

        save_btn=findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onSaveButtonClick(v);
            }
        });

    }
    // TODO 01. DONE Create a new layout file to define the GUI elements of the ContactFormActivity.
    // TODO 02. DONE Define the basic skeleton of the ContactFormActivity. Inflate the layout defined in the first step to display the GUI elements on screen.
    // TODO 06. DONE Create a new method that reads the values in the several EditText elements of the layout and then uses the Contact class to send those data to ContactInfoActivity

    public void onSaveButtonClick(View view){
    Intent intent = new Intent(this, ContactInfoActivity.class);
    //EditTexts
    EditText fname = findViewById(R.id.first_name_edit);
    EditText lname = findViewById(R.id.last_name_edit);
    EditText phoneNumber = findViewById(R.id.phone_edit);
    EditText email = findViewById(R.id.email_edit);
    EditText address = findViewById(R.id.address_edit);
    EditText website = findViewById(R.id.website_edit);

    Contact contact = new Contact();
    contact.setFirstName(fname.getText().toString());
    contact.setLastName(lname.getText().toString());
    contact.setPhoneNumber(phoneNumber.getText().toString());
    contact.setEmailAddress(email.getText().toString());
    contact.setAddress(address.getText().toString());
    contact.setWebsite(website.getText().toString());

    intent.putExtra(EXTRA_CONTACT, contact);
    startActivity(intent);
    }


}
