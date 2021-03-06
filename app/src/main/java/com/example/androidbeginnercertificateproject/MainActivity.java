package com.example.androidbeginnercertificateproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfPeople) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfPeople);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 10) {
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 0) {
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the submit button is clicked.
     */
    public void submitOrder(View view) {
        // Get user's name
        EditText nameField = (EditText) findViewById(R.id.name_field);
        Editable nameEditable = nameField.getText();
        String name = nameEditable.toString();
        //print the summary
        TextView summarySpace = (TextView) findViewById(R.id.summary_space);
        String summary = "Thank you " + name + "!" + "\n" + "Your order has been confirmed for " + quantity;
        summarySpace.setText(summary);

        // Use an intent to launch an email app.
        // Send the order summary in the email body.
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Your invitation card is ready");
        intent.putExtra(Intent.EXTRA_TEXT, summary);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }



}