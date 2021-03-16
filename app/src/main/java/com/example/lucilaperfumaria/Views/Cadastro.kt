package com.example.lucilaperfumaria.Views

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.lucilaperfumaria.Bd.Pessoa
import com.example.lucilaperfumaria.Bd.PessoaBDOpener
import com.example.lucilaperfumaria.R
import com.example.lucilaperfumaria.databinding.ActivityCadastroBinding
import java.lang.Exception

class Cadastro : AppCompatActivity() {
    lateinit var binding:ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_cadastro)

        binding.buttonCadastrar.setOnClickListener{
            try {
                var nome=binding.editTextNome.text.toString()
                var produto=binding.editTextProduto.text.toString()
                var cotato=binding.editTextContato.text.toString()
                var totalCompra=binding.editTextTotalCompra.text.toString()
                var totalPago= binding.editTextTotalPago.text.toString()
               // Toast.makeText(this,totalCompra.toString()+totalPago.toString(),Toast.LENGTH_LONG).show()
                var pessoa = Pessoa(0,nome ,produto, cotato,totalCompra.toFloat() ,totalPago.toFloat())
                val db = PessoaBDOpener(this)
                db.insert(pessoa)

            }catch (e:Exception){
                Toast.makeText(this,"erro no bd",Toast.LENGTH_LONG).show()
            }



            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        binding.buttonCancelar.setOnClickListener{
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }

    }
}