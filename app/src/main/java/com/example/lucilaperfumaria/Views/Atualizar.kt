package com.example.lucilaperfumaria.Views

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.lucilaperfumaria.Bd.Pessoa
import com.example.lucilaperfumaria.Bd.PessoaBDOpener
import com.example.lucilaperfumaria.R
import com.example.lucilaperfumaria.databinding.ActivityAtualizarBinding
import java.lang.Exception

class Atualizar : AppCompatActivity() {

    lateinit var pessoa:Pessoa
    lateinit var binding:ActivityAtualizarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_atualizar)
        val db = PessoaBDOpener(this)

        binding.buttonCadastrar.isEnabled=(false)

        binding.buttonBuscar.setOnClickListener {
        var cont:Int=binding.editTextId.text.toString().toInt()

            if(cont>0){
        try {
            pessoa= db.findById(cont)
            exibirTela(pessoa)

        }catch (e:Exception){
            Toast.makeText(this,"Id não existe",Toast.LENGTH_LONG).show()
        }

            }
        }
        binding.buttonCadastrar.setOnClickListener {
             try {
                 var nome=binding.editTextNome.text.toString()
                 var produto=binding.editTextProduto.text.toString()
                 var cotato=binding.editTextContato.text.toString()
                 var totalCompra=binding.editTextTotalCompra.text.toString()
                 var totalPago= binding.editTextTotalPago.text.toString()
                 var pessoa = Pessoa(pessoa.id,nome ,produto, cotato,totalCompra.toFloat() ,totalPago.toFloat())
                 val db = PessoaBDOpener(this)
                 db.update(pessoa)
             }catch (e:Exception){
                 Toast.makeText(this,"erro editar",Toast.LENGTH_LONG).show()
             }



            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        binding.buttonCancelar.setOnClickListener {
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }

    }
    fun exibirTela(pessoa: Pessoa){


        binding.editTextNome.setText(pessoa.nome.toString())
        binding.editTextContato.setText(pessoa.contato.toString())
        binding.editTextProduto.setText(pessoa.produto.toString())
        binding.editTextTotalCompra.setText(pessoa.totalCompra.toString())
        binding.editTextTotalPago.setText(pessoa.totalPago.toString())
        binding.buttonCadastrar.isEnabled=(true)
    }
}