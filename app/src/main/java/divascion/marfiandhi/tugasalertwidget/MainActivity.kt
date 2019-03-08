package divascion.marfiandhi.tugasalertwidget

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.alert_form.view.*


class MainActivity : AppCompatActivity() {

    private lateinit var religion:Spinner
    private lateinit var gender:String
    private lateinit var name:String
    private lateinit var nim:String

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        alert_button.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val view = inflater.inflate(R.layout.alert_form, null)
            alert.setView(view)
            alert.setTitle("ALERT WIDGET BIODATA")
            alert.setIcon(R.drawable.ic_person_add_black_24dp)
            religion = view.findViewById(R.id.alert_spinner)
            val adapter = ArrayAdapter.createFromResource(this, R.array.religion_array, android.R.layout.simple_spinner_item)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            religion.adapter = adapter
            alert.setPositiveButton("Submit") { dialog, _ ->
                nim = if(view.alert_nim.text.isNullOrEmpty()) {
                    "H131xxxxx"
                }else {
                    view.alert_nim.text.toString()
                }
                name = if(view.alert_nama.text.isNullOrEmpty()) {
                    "Unknown"
                }else {
                    view.alert_nama.text.toString()
                }
                gender = when {
                    view.alert_male.isChecked -> "Male"
                    view.alert_female.isChecked -> "Female"
                    else -> "Let it be a mystery"
                }
                val religionText = view.alert_spinner.selectedItem.toString()
                dialog.dismiss()
                Toast.makeText(this, "NIM = $nim\nNAME = $name\nGENDER = $gender\nRELIGION = $religionText", Toast.LENGTH_LONG).show()
            }
            alert.setNegativeButton("I'm Not Sure") { dialog, _ ->  dialog.dismiss()}.show()
        }
    }
}
