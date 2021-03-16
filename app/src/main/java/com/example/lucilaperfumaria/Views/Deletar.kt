package com.example.lucilaperfumaria.Views

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.lucilaperfumaria.Bd.PessoaBDOpener
import com.example.lucilaperfumaria.R
import com.example.lucilaperfumaria.databinding.ActivityCadastradosBinding
import com.example.lucilaperfumaria.databinding.ActivityDeletarBinding
import java.lang.Exception

class Deletar : AppCompatActivity() {

    lateinit var binding: ActivityDeletarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=DataBindingUtil.setContentView(this,R.layout.activity_deletar)


        binding.buttonDelete.setOnClickListener {

            var del=binding.editTextId.text.toString()
            var num= del.toInt()

            try {
                var db= PessoaBDOpener(this)
                var pessoa= db.findById(num)
                db.delete(pessoa)

            }catch (e:Exception){
                Toast.makeText(this,"não foi possivel deletar esse cliente!",Toast.LENGTH_LONG).show()
            }

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        binding.buttonCancelar.setOnClickListener {
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }
    }
}