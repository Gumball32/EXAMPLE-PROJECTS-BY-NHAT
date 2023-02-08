import React, { useReducer } from "react";
import CartContext from "./cart-context";

const defaultCartState = {
    items: [],
    totalAmount: 0
}

const cartReducer = (state, action) => {
    if (action.type === "ADD") {
        const newTotalAmount = state.totalAmount + action.item.price * action.item.amount;
        const isExistingIndex = state.items.findIndex(item => item.id === action.item.id);
        const isExistingCartItem = state.items[isExistingIndex];
        let updatedItems;

        if (isExistingCartItem) {
            const updatedItem = {
                ...isExistingCartItem,
                amount: isExistingCartItem.amount + action.item.amount
            }
            updatedItems = [...state.items];
            updatedItems[isExistingIndex] = updatedItem;
        } else {
            updatedItems = state.items.concat(action.item);
        }

        return {
            items: updatedItems,
            totalAmount: newTotalAmount
        };
    }

    if (action.type === "REMOVE") {
        const existingCartIndex = state.items.findIndex(item => item.id === action.id);
        const existingItem = state.items[existingCartIndex];
        const updatedTotalAmount = state.totalAmount - existingItem.price;
        let updatedItems;
        if (existingItem.amount === 1) {
            updatedItems = state.items.filter(item => item.id != action.id);
        } else {
            const updatedItem = {...existingItem, amount: existingItem.amount - 1}
            updatedItems = [...state.items];
            updatedItems[existingCartIndex] = updatedItem;
        }
        return {
            items: updatedItems,
            totalAmount: updatedTotalAmount
        };
    }

    return defaultCartState;
}

const CartProvider = props => {
    const [cartState, dispatchCartAction] = useReducer(cartReducer, defaultCartState);

    const addItemHandler = item => {
        dispatchCartAction({
            type: 'ADD', item: item
        });
    };

    const removeItemHandler = id => {
        dispatchCartAction({
            type: 'REMOVE', id: id
        });
    };

    const cartContext = {
        items: cartState.items,
        totalAmount: cartState.totalAmount,
        addItem: addItemHandler,
        removeItem: removeItemHandler
    };

    return (
        <CartContext.Provider value={cartContext}>
            {props.children}
        </CartContext.Provider>

    );
}

export default CartProvider;