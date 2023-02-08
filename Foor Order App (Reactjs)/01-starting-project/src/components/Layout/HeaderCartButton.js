import React, { useContext, useEffect, useState } from "react";
import CartContext from "../../store/cart-context";
import CartIcon from "../Cart/CartIcon";
import classes from './HeaderCartButton.module.css';

const HeaderCartButton = props => {
    const [btnIsHighLighted, setBtnIsHighLighted] = useState(false);
    const cartContext = useContext(CartContext);

    const numberOfCartItems = cartContext.items.reduce((curNumber, item) => {
        return curNumber + item.amount
    }, 0)

    const btnClasses = `${classes.button} ${btnIsHighLighted ? classes.bump : ''}`;

    const {items} = cartContext;

    useEffect(() => {
        if (cartContext.items.length === 0) {
            return;
        }
        setBtnIsHighLighted(true);

        const timer = setTimeout(() => {
            setBtnIsHighLighted(false);
        }, 300);


        return () => {
            clearTimeout(timer);
        };
    }, [items]);

    return <button className={btnClasses} onClick={props.onClick}>
        <span className={classes.icon}>
            <CartIcon></CartIcon>
        </span>
        <span>
            Your Cart
        </span>
        <span className={classes.badge}>
            {numberOfCartItems}
        </span>
    </button>
}

export default HeaderCartButton;