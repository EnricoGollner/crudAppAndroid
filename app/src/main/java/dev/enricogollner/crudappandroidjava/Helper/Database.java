package dev.enricogollner.crudappandroidjava.Helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "compostos.db";
    private static final int DATABASE_VERSION = 1;

    // Tabela de compostos
    public static final String TABLE_NAME_1 = "compostos";
    public static final String COLUMN_ID_1 = "id";
    public static final String COLUMN_NOME_COMPOSTO = "nome";

    public static final String COLUMN_FORMULA = "formula";
    public static final String COLUMN_UNIDADE_MEDIDA = "unidadeMedida";
    private static final String TABLE_CREATE_1 =
            "CREATE TABLE " + TABLE_NAME_1 + " (" +
                    COLUMN_ID_1 + " INTEGER PRIMARY KEY, " +
                    COLUMN_NOME_COMPOSTO + " TEXT, " +
                    COLUMN_FORMULA + " TEXT, " +
                    COLUMN_UNIDADE_MEDIDA + " TEXT);";


    // Tabela de registros de saída
    public static final String TABLE_NAME_2 = "registrosSaida";
    public static final String COLUMN_ID_2 = "idRegistro";

    public static final String COLUMN_COMPOSTO_ID = "idComposto";

    public static final String COLUMN_QUANTIDADE = "quantidade";

    public static final String COLUMN_OBSERVACOES = "observacoes";


    private static final String TABLE_CREATE_2 =
            "CREATE TABLE " + TABLE_NAME_2 + " (" +
                    COLUMN_ID_2 + " INTEGER PRIMARY KEY, " +
                    COLUMN_COMPOSTO_ID + " INTEGER, " +
                    COLUMN_QUANTIDADE + " DOUBLE, " +
                    COLUMN_OBSERVACOES + " TEXT, " +
                    "FOREIGN KEY(" + COLUMN_COMPOSTO_ID + ") REFERENCES " + TABLE_NAME_1 + "(" + COLUMN_ID_1 + "));";


    // Instância única da classe
    private static Database instance;

    // Construtor privado para evitar instanciação direta
    private Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Método para obter a instância única da classe
    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = new Database(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_1);
        db.execSQL(TABLE_CREATE_2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualizações futuras do banco de dados, se necessário
        // Aqui você pode adicionar lógica para migrar dados existentes, se necessário
    }


}
