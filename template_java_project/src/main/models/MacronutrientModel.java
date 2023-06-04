package main.models;

import java.util.Objects;

public class MacronutrientModel {
    private int calories;
    private int protein;
    private int fat;
    private int carbohydrates;

    public MacronutrientModel() {}

    public MacronutrientModel(Builder builder) {
        this.calories = builder.calories;
        this.protein = builder.protein;
        this.fat = builder.fat;
        this.carbohydrates = builder.carbohydrates;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MacronutrientModel)) return false;
        MacronutrientModel that = (MacronutrientModel) o;
        return getCalories() == that.getCalories() && getProtein() == that.getProtein() && getFat() == that.getFat() && getCarbohydrates() == that.getCarbohydrates();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCalories(), getProtein(), getFat(), getCarbohydrates());
    }

    @Override
    public String toString() {
        return "MacronutrientModel{" +
                "calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", carbohydrates=" + carbohydrates +
                '}';
    }
    public static Builder builder() { return new Builder(); }
    public static final class Builder {
        private int calories;
        private int protein;
        private int fat;
        private int carbohydrates;

        public Builder withCalories(int caloriesToUse) {
            this.calories = caloriesToUse;
            return this;
        }

        public Builder withProtein(int proteinToUse) {
            this.protein = proteinToUse;
            return this;
        }

        public Builder withFat(int fatToUse) {
            this.fat = fatToUse;
            return this;
        }

        public Builder withCarbohydrates(int carbohydratesToUse) {
            this.carbohydrates = carbohydratesToUse;
            return this;
        }
        public MacronutrientModel build() { return new MacronutrientModel(this); }
    }
}
