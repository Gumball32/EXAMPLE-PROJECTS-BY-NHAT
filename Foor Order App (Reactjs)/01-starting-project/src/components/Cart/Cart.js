import { useContext } from 'react';
import CartContext from '../../store/cart-context';
import Modal from '../UI/Modal';
import classes from './Cart.module.css';
import CartItem from './CartItem';

const Cart = props => {
    const context = useContext(CartContext);

    const totalAmount = `$${context.totalAmount.toFixed(2)}`;
    const hasItem = context.items.length > 0;

    const cartItemRemoveHandler = id => {
        context.removeItem(id)
    }

    const cartItemAddHandler = item => {
        context.addItem({...item, amount: 1});
    }

    const cartItems = <ul className={classes["cart-items"]}>
        {context.items.map(
            (item) => (
                <CartItem
                key={item.id} price={item.price} name={item.name} amount={item.amount}
                onRemove={cartItemRemoveHandler.bind(null, item.id)} onAdd={cartItemAddHandler.bind(null, item)}
                ></CartItem>
            )
        )}
    </ul>

    return (
        <Modal onHideCart={props.onHideCart}>
            {cartItems}
            <div className={classes.total}>
                <span>Total Amount</span>
                <span>{totalAmount}</span>
            </div>
            <div className={classes.actions}>
                <button className={classes["button--alt"]} onClick={props.onHideCart}>Close</button>
                {hasItem && <button className={classes.button}>Order</button>}
            </div>
        </Modal>
    );
}

export default Cart;