package com.example.library.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 目录资源获取工具类
 * 描述：
 * <li>Auto copy databse form assets to /data/data/package_name/databases</li>
 * <li>You can use it like {@link SQLiteDatabase}, use {@link #getWritableDatabase()} to create and/or open a database
 * that will be used for reading and writing. use {@link #getReadableDatabase()} to create and/or open a database that
 * will be used for reading only.</li>
 * 时间：2016年9月13日
 */
public class AssetDatabaseOpenHelper {

    private Context context;
    private String  databaseName;

    public AssetDatabaseOpenHelper(Context context, String databaseName) {
        this.context = context;
        this.databaseName = databaseName;
    }

    /**
     * Create and/or open a database that will be used for reading and writing.
     *
     * @return 返回数据库对象
     * @throws RuntimeException if cannot copy database from assets
     * @throws SQLiteException if the database cannot be opened
     */
    public synchronized SQLiteDatabase getWritableDatabase() {
        File dbFile = context.getDatabasePath(databaseName);
        if (dbFile != null && !dbFile.exists()) {
            try {
                copyDatabase(dbFile);
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
    }

    /**
     * Create and/or open a database that will be used for reading only.
     *
     * @return 返回读的数据库对象
     * @throws RuntimeException if cannot copy database from assets
     * @throws SQLiteException if the database cannot be opened
     */
    public synchronized SQLiteDatabase getReadableDatabase() {
        File dbFile = context.getDatabasePath(databaseName);
        if (dbFile != null && !dbFile.exists()) {
            try {
                copyDatabase(dbFile);
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READONLY);
    }

    /**
     * @return t返回数据库名称
     */
    public String getDatabaseName() {
        return databaseName;
    }

    private void copyDatabase(File dbFile) throws IOException {
        InputStream stream = context.getAssets().open(databaseName);
        FileUtils.writeFile(dbFile, stream);
        stream.close();
    }

    /**
     * 开始导出数据 此操作比较耗时,建议在线程中进行
     *
     * @param context      上下文
     * @param targetFile   目标文件
     * @param databaseName 要拷贝的数据库文件名
     * @return 是否倒出成功
     */
    public boolean startExportDatabase(Context context, String targetFile, String databaseName) {
        if (!Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            return false;
        }
        String sourceFilePath = Environment.getDataDirectory() + "/data/"
                + context.getPackageName() + "/databases/" + databaseName;
        String destFilePath = Environment.getExternalStorageDirectory()
                + (TextUtils.isEmpty(targetFile) ? (context.getPackageName() + ".db")
                : targetFile);
        return FileUtils.copyFile(sourceFilePath, destFilePath);
    }
}
