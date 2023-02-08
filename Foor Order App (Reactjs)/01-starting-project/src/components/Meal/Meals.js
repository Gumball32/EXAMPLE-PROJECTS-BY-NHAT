import React, { Fragment } from "react";
import MealsSummary from "./MealsSummary";
import AvailiableMeals from './AvailableMeals';

const Meals = () => {
    return (
        <Fragment>
            <MealsSummary></MealsSummary>
            <AvailiableMeals></AvailiableMeals>
        </Fragment>
    )
}

export default Meals;