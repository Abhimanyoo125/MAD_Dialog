package com.example.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonAlertDialog = findViewById(R.id.buttonAlertDialog);
        Button buttonProgressDialog = findViewById(R.id.buttonProgressDialog);
        Button buttonDatePickerDialog = findViewById(R.id.buttonDatePickerDialog);
        Button buttonTimePickerDialog = findViewById(R.id.buttonTimePickerDialog);

        // Show Alert Dialog
        buttonAlertDialog.setOnClickListener(v -> showAlertDialog());

        // Show Progress Dialog
        buttonProgressDialog.setOnClickListener(v -> showProgressDialog());

        // Show Date Picker Dialog
        buttonDatePickerDialog.setOnClickListener(v -> showDatePickerDialog());

        // Show Time Picker Dialog
        buttonTimePickerDialog.setOnClickListener(v -> showTimePickerDialog());
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert Dialog")
                .setMessage("This is an alert dialog.")
                .setPositiveButton("OK", (dialog, which) -> Toast.makeText(MainActivity.this, "OK Clicked", Toast.LENGTH_SHORT).show())
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void showProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Simulate a long operation
        new Thread(() -> {
            try {
                Thread.sleep(3000); // Simulate a delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(() -> progressDialog.dismiss());
        }).start();
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            Toast.makeText(MainActivity.this, "Selected Date: " + date, Toast.LENGTH_SHORT).show();
        }, year, month, day);

        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, selectedHour, selectedMinute) -> {
            String time = selectedHour + ":" + String.format("%02d", selectedMinute);
            Toast.makeText(MainActivity.this, "Selected Time: " + time, Toast.LENGTH_SHORT).show();
        }, hour, minute, true);

        timePickerDialog.show();
    }
}
