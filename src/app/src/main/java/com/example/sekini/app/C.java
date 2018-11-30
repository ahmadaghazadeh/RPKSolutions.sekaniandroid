package com.example.sekini.app;

public class C {
    public static String UrlApi = "https://tkdictionary.azurewebsites.net/api/api/";
    public static String UrlAuth = "https://tkdictionary.azurewebsites.net/auth/connect/";
    public static int Game1PageCount = 7;

    public enum TableName {
        EnglishWords,
        SekaniCategories,
        SekaniDictionary,
        SekaniForms,
        SekaniLevels,
        SekaniRootImages,
        SekaniRoots_EnglishWords,
        SekaniRoots_Topics,
        SekaniRoots,
        SekaniWordAttributes,
        SekaniWordAudios,
        SekaniWordExampleAudios,
        SekaniWordExamples,
        SekaniWords,
        Topics,
    }

    public enum Attribute {
        EnglishWords,
        SekaniCategories,
        SekaniDictionary,
        SekaniForms,
        SekaniLevels,
        SekaniRootImages,
        SekaniRoots_EnglishWords,
        SekaniRoots_Topics,
        SekaniRoots,
        SekaniWordAttributes,
        SekaniWordAudios,
        SekaniWordExampleAudios,
        SekaniWordExamples,
        SekaniWords,
        Topics,
    }

    public enum GameType {
        Game1,
        Game2
    }
}
