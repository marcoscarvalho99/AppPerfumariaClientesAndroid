package com.example.lucilaperfumaria.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.lucilaperfumaria.Bd.Pessoa
import com.example.lucilaperfumaria.Bd.PessoaBDOpener
import com.example.lucilaperfumaria.R
import com.example.lucilaperfumaria.databinding.ActivityCadastradosBinding
import com.example.lucilaperfumaria.databinding.ActivityCadastroBinding

class Cadastrados : AppCompatActivity() {
    var cont:Int=0;
    lateinit var binding: ActivityCadastradosBinding
    lateinit var lista:MutableList<Pessoa>
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_cadastrados)

        val db = PessoaBDOpener(this)

        fecharBt1()
        fecharBtn2()
        try {

            //var pessoa= db.findById(cont)
            lista = db.findAll()
            var pe = lista.get(cont)
            exibirTela(pe)
            //var print = lista.get(0);
            //Toast.makeText(this,lista.size.toString(),Toast.LENGTH_LONG).show()
        }catch (e:Exception){

        }


        binding.buttonProximo.setOnClickListener {
            contadorProximo()

            lista = db.findAll()
            var p = lista.get(cont)
            exibirTela(p)
            fecharBtn2()
            fecharBt1()
        }
        binding.buttonAnterior.setOnClickListener {
            contadorAnterio()
           // var pessoa = db.findAll()
           // var test: mutableListOf(db.findAll())
            lista = db.findAll()
            var p = lista.get(cont)



            //Toast.makeText(this,print.nome.toString(),Toast.LENGTH_LONG).show()
            if(cont==lista.size){
                binding.buttonProximo.isEnabled=(false)
            }
            exibirTela(p)
            fecharBtn2()
            fecharBt1()
        }

    }
    fun contadorProximo(){
        if (quantidadeMaxima()>cont)
            cont++
    }
    fun contadorAnterio(){
        if (cont>0){
            cont--
        }
    }

    fun quantidadeMaxima():Int {
        var max = 0
        val db = PessoaBDOpener(this)
        try {
            var pessoa = db.findAll()

            pessoa.forEach {
                max++;

            }

            }catch (e:Exception){

        }


        return max-1
    }

    fun fecharBt1(){
        if(cont==0 ){
            binding.buttonAnterior.isEnabled=(false)
        }
        if(cont>0){
            binding.buttonAnterior.isEnabled=true
        }
    }
    fun fecharBtn2() {
        if (cont == quantidadeMaxima()) {
            binding.buttonProximo.isEnabled = (false)

        }
        if (cont < quantidadeMaxima()) {
            binding.buttonProximo.isEnabled = true
        }
    }
    fun exibirTela(pessoa: Pessoa){

        binding.textViewId.text=pessoa.id.toString()
        binding.textViewNome.text=pessoa.nome.toString()
        binding.textViewContato.text=pessoa.contato.toString()
        binding.textViewProduto.text=pessoa.produto.toString()
        binding.textViewTotalCompra.text=pessoa.totalCompra.toString() + " R$"
        binding.textViewTotalPago.text=pessoa.totalPago.toString() + " R$"
        calcularFaltante(pessoa)
    }

    fun calcularFaltante(pessoa:Pessoa){
        var valorCompra:Float=pessoa.totalCompra.toFloat()
        var valorTotal=pessoa.totalPago.toFloat()

        if(valorCompra > valorTotal){
            var pagar= valorCompra - valorTotal
            binding.textViewTotalfalta.text=pagar.toString() + " R$"
        }else{
            binding.textViewTotalfalta.text=R.string.compraErro.toString()
        }

    }

}