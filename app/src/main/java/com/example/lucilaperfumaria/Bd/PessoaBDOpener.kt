package com.example.lucilaperfumaria.Bd


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log

class PessoaBDOpener (context : Context) : SQLiteOpenHelper(context, PessoaContrato.DATABASE_NAME, null, PessoaContrato.DATA_BASE_VERSION) {


    val TAG = "sql"
    val SQL_CREATE_TABLE =
        "CREATE TABLE ${PessoaContrato.LivroEntry.TABLE_NAME}" +
                "( ${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                " ${PessoaContrato.LivroEntry.NOME} TEXT," +
                " ${PessoaContrato.LivroEntry.PRODUTO} TEXT," +
                " ${PessoaContrato.LivroEntry.CONTATO} TEXT," +
                " ${PessoaContrato.LivroEntry.TOTALCOMPRA} FLOAT," +
                " ${PessoaContrato.LivroEntry.TOTALPAGO} FLOAT" +
                ")"
    val SQL_DROP_TABLE =
        "DROP TABLE ${PessoaContrato.LivroEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        Log.i(TAG, "Banco de dados Criado")
        db.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db.execSQL(SQL_DROP_TABLE)
            db.execSQL(SQL_CREATE_TABLE)
        }
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db.execSQL(SQL_DROP_TABLE)
            db.execSQL(SQL_CREATE_TABLE)
        }
    }

    fun insert(pessoa: Pessoa) {
        var banco: SQLiteDatabase = writableDatabase
        try {

            var values = ContentValues()
            values.put(PessoaContrato.LivroEntry.NOME, pessoa.nome)
            values.put(PessoaContrato.LivroEntry.CONTATO, pessoa.contato)
            values.put(PessoaContrato.LivroEntry.PRODUTO, pessoa.produto)
            values.put(PessoaContrato.LivroEntry.TOTALCOMPRA, pessoa.totalCompra)
            values.put(PessoaContrato.LivroEntry.TOTALPAGO, pessoa.totalPago)

            banco.insert(PessoaContrato.LivroEntry.TABLE_NAME, null, values)

        } finally {
            banco.close()
        }
    }

    fun update(pessoa: Pessoa) {
        var banco: SQLiteDatabase = writableDatabase
        try {

            var values = ContentValues()
            values.put(PessoaContrato.LivroEntry.NOME, pessoa.nome)
            values.put(PessoaContrato.LivroEntry.CONTATO, pessoa.contato)
            values.put(PessoaContrato.LivroEntry.PRODUTO, pessoa.produto)
            values.put(PessoaContrato.LivroEntry.TOTALCOMPRA, pessoa.totalCompra)
            values.put(PessoaContrato.LivroEntry.TOTALPAGO, pessoa.totalPago)

            var selection = "${BaseColumns._ID} = ?"
            var whereArgs = arrayOf("${pessoa.id}")

            banco.update(PessoaContrato.LivroEntry.TABLE_NAME, values, selection, whereArgs)

        } finally {
            banco.close()
        }
    }

    fun delete(pessoa: Pessoa) {
        var banco: SQLiteDatabase = writableDatabase
        try {

            var selection = "${BaseColumns._ID} = ?"
            var whereArgs = arrayOf("${pessoa.id}")
            Log.i("AULABANCO", "Delete livro id = ${pessoa.id}")
            banco.delete(PessoaContrato.LivroEntry.TABLE_NAME, selection, whereArgs)

        } finally {
            banco.close()
        }
    }

    fun findByName(nome: String): Pessoa {
        var banco: SQLiteDatabase = readableDatabase
        try {

            var selection = "${PessoaContrato.LivroEntry.NOME} = ?"
            var whereArgs = arrayOf("${nome}")
            val cursor: Cursor = banco.query(PessoaContrato.LivroEntry.TABLE_NAME, null, selection, whereArgs, null, null, null, null)

            cursor.moveToFirst()

            var pessoa = Pessoa()
            pessoa.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            pessoa.nome = cursor.getString(cursor.getColumnIndex(PessoaContrato.LivroEntry.NOME))
            pessoa.produto = cursor.getString(cursor.getColumnIndex(PessoaContrato.LivroEntry.PRODUTO))
            pessoa.contato = cursor.getString(cursor.getColumnIndex(PessoaContrato.LivroEntry.CONTATO))
            pessoa.totalCompra = cursor.getFloat(cursor.getColumnIndex(PessoaContrato.LivroEntry.TOTALCOMPRA))
            pessoa.totalPago = cursor.getFloat(cursor.getColumnIndex(PessoaContrato.LivroEntry.TOTALPAGO))


            return pessoa

        } finally {
            banco.close()
        }
    }

    fun findById(id: Int): Pessoa {
        var banco: SQLiteDatabase = readableDatabase
        try {

            var selection = "${BaseColumns._ID} = ?"
            var whereArgs = arrayOf("${id}")
            val cursor: Cursor = banco.query(PessoaContrato.LivroEntry.TABLE_NAME, null, selection, whereArgs, null, null, null, null)

            cursor.moveToFirst()

            var pessoa = Pessoa()
            pessoa.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            pessoa.nome = cursor.getString(cursor.getColumnIndex(PessoaContrato.LivroEntry.NOME))
            pessoa.produto = cursor.getString(cursor.getColumnIndex(PessoaContrato.LivroEntry.PRODUTO))
            pessoa.contato = cursor.getString(cursor.getColumnIndex(PessoaContrato.LivroEntry.CONTATO))
            pessoa.totalCompra = cursor.getFloat(cursor.getColumnIndex(PessoaContrato.LivroEntry.TOTALCOMPRA))
            pessoa.totalPago = cursor.getFloat(cursor.getColumnIndex(PessoaContrato.LivroEntry.TOTALPAGO))

            return pessoa

        } finally {
            banco.close()
        }
    }

    fun findAll(): ArrayList<Pessoa> {
        var banco: SQLiteDatabase = readableDatabase
        try {

            val cursor: Cursor = banco.query(PessoaContrato.LivroEntry.TABLE_NAME, null, null, null, null, null, null, null)

            var listaPessoa = ArrayList<Pessoa>()

            while (cursor.moveToNext()) {
                var pessoa = Pessoa()

                pessoa.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
                pessoa.nome = cursor.getString(cursor.getColumnIndex(PessoaContrato.LivroEntry.NOME))
                pessoa.produto = cursor.getString(cursor.getColumnIndex(PessoaContrato.LivroEntry.PRODUTO))
                pessoa.contato = cursor.getString(cursor.getColumnIndex(PessoaContrato.LivroEntry.CONTATO))
                pessoa.totalCompra = cursor.getFloat(cursor.getColumnIndex(PessoaContrato.LivroEntry.TOTALCOMPRA))
                pessoa.totalPago = cursor.getFloat(cursor.getColumnIndex(PessoaContrato.LivroEntry.TOTALPAGO))

                listaPessoa.add(pessoa)
            }

            return listaPessoa

        } finally {
            banco.close()
        }
    }
}
