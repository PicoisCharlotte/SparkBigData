package com.sparkbigdata.tools;

        import com.google.gson.internal.$Gson$Types;
        import org.apache.spark.sql.Dataset;
        import org.apache.spark.sql.Encoders;
        import org.apache.spark.sql.SparkSession;
        import org.apache.spark.sql.types.DataTypes;
        import org.apache.spark.sql.types.Metadata;
        import org.apache.spark.sql.types.StructField;
        import org.apache.spark.sql.types.StructType;

public class CsvReaderWithType {
    private final static String _food = CsvReaderWithType.class.getClassLoader().getResource("food.csv").getFile();
    private final static String _typeFood = CsvReaderWithType.class.getClassLoader().getResource("typeFood.csv").getFile();
    private final static String _drink = CsvReaderWithType.class.getClassLoader().getResource("drink.csv").getFile();

    public void mappingFood() {
        SparkSession session = SparkSessionFactory.getSession();
        Dataset<Food> foods = session
                .read().option("header","true").schema(new StructType(new StructField[]{
                        new StructField("ID", DataTypes.IntegerType, true, Metadata.empty()),
                        new StructField("NAME", DataTypes.StringType, true, Metadata.empty())}
                )).csv(_food)
                .as(Encoders.bean(Food.class));
        foods.show();
    }

    public void mappingTypeFood() {
        SparkSession session = SparkSessionFactory.getSession();
        Dataset<TypeFood> typeFoods = session
                .read().option("header","true").schema(new StructType(new StructField[]{
                        new StructField("ID", DataTypes.IntegerType, true, Metadata.empty()),
                        new StructField("TYPE", DataTypes.StringType, true, Metadata.empty())}
                )).csv(_typeFood)
                .as(Encoders.bean(TypeFood.class));
        typeFoods.show();
    }

    public void mappingDrink() {
        SparkSession session = SparkSessionFactory.getSession();
        Dataset<Drink> drinks = session
                .read().option("header","true").schema(new StructType(new StructField[]{
                        new StructField("ID", DataTypes.IntegerType, true, Metadata.empty()),
                        new StructField("NAME", DataTypes.StringType, true, Metadata.empty())}
                )).csv(_drink)
                .as(Encoders.bean(Drink.class));
        drinks.show();
    }
}
