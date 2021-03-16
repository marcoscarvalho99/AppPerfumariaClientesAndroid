package com.example.lucilaperfumaria.Bd


import android.provider.BaseColumns

object PessoaContrato {

    const val DATABASE_NAME = "pessoadb.sqlite"
    const val DATA_BASE_VERSION = 1

    object LivroEntry : BaseColumns {
        const val TABLE_NAME = "Pessoa"
        const val NOME = "nome"
        const val CONTATO = "contato"
        const val PRODUTO = "produto"
        const val TOTALCOMPRA = "totalCompra"
        const val TOTALPAGO = "totalPago"

    }
}

