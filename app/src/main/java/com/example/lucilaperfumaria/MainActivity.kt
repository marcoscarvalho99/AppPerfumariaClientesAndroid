package com.example.lucilaperfumaria

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.lucilaperfumaria.Bd.Pessoa
import com.example.lucilaperfumaria.Bd.PessoaBDOpener
import com.example.lucilaperfumaria.Views.Atualizar
import com.example.lucilaperfumaria.Views.Cadastrados
import com.example.lucilaperfumaria.Views.Cadastro
import com.example.lucilaperfumaria.Views.Deletar
import com.example.lucilaperfumaria.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner =this


        binding.buttonCadastrar.setOnClickListener {
            var intent = Intent(this, Cadastro::class.java)
            startActivityForResult(intent,1)
        }

        binding.buttonDeletar.setOnClickListener {
            var intent = Intent(this, Deletar::class.java)
            startActivityForResult(intent,3)
        }

        binding.buttonAtualizar.setOnClickListener {
            var intent = Intent(this, Atualizar::class.java)
            startActivityForResult(intent,2)
        }

        binding.buttonVisualizar.setOnClickListener {
            var intent = Intent(this, Cadastrados::class.java)
            startActivityForResult(intent,4)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        when (resultCode) {
            Activity.RESULT_CANCELED -> {
                Snackbar.make(binding.view,"cancelado", Snackbar.LENGTH_LONG).show()
            }
            Activity.RESULT_OK -> {

                when(requestCode){
                    1 ->{
                        Snackbar.make(binding.view,"cadastrado", Snackbar.LENGTH_LONG).show()
                    }
                    2->{
                        Snackbar.make(binding.view,"edição realizada com sucesso!", Snackbar.LENGTH_LONG).show()
                    }
                    //resposta7
                    3 ->{
                       Snackbar.make(binding.view,"exclusão realizada com sucesso!", Snackbar.LENGTH_LONG).show()
                    }
                }

            }

        }
    }

}