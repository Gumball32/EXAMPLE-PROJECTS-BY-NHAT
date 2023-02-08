import React, { useRef, useState } from "react";
import Input from "../../UI/Input";
import classes from "./MealItemForm.module.css";

const MealItemForm = props => {
    const [isValid, setIsValid] = useState(false);
    const amountInputRef = useRef();

    const submitHandler = event => {
        event.preventDefault();

        const enteredAmount = amountInputRef.current.value;
        const enteredNumber = +enteredAmount;

        if (enteredAmount.trim().length === 0 || enteredAmount.trim().length < 1 || enteredAmount.trim().length > 5) {
            setIsValid(false);
            return;
        }

        props.onAddToCart(enteredNumber);
    }

    return (
        <form className={classes.form} onSubmit={submitHandler}>
            <Input
            ref = {amountInputRef}
            label='Amount'
            input={{
                id: 'amount_' + props.id,
                type: 'number',
                min: '1',
                max: '5',
                step: '1',
                defaultValue: '1'
            }}
            ></Input>
            <button>+ Add</button>
            {!isValid && <p>Enter valid amount</p>}
        </form>
    );
}

export default MealItemForm;