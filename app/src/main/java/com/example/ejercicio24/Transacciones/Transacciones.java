package com.example.ejercicio24.Transacciones;

public class Transacciones {

    public static final String tablaFirma = "tblfirma";

    public static final String id = "id";
    public static final String descripcion = "descripcion";
    public static final String firma = "firma";

    public static final String CreateTableFirma = "CREATE TABLE tblfirma(id INTEGER PRIMARY KEY AUTOINCREMENT, firma BLOB,descripcion TEXT)";

    public static final String DropTableFirma  = "DROP TABLE IF EXISTS tblfirma";

    public static final String NameDataBase = "DBpm12";
    public static final String test1 = "select * from tblfirma";
}

