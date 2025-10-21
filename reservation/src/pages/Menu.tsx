import React from 'react';
import '../styles/Menu.css';

interface MenuItem {
  name: string;
  price: string;
}

const menuItems: MenuItem[] = [
  { name: '야채 토스트', price: '3,000원' },
  { name: '돌솥비빔밥', price: '8,000원' },
  { name: '야채비빔밥', price: '7,000원' },
  { name: '된장찌개', price: '7,000원' },
  { name: '육개장', price: '8,000원' },
  { name: '김치비빔국수', price: '6,500원' },
  { name: '생선까스', price: '8,000원' },
  { name: '고구마치즈돈까스', price: '9,000원' },
  { name: '치즈돈까스', price: '8,500원' },
  { name: '등심돈까스', price: '8,000원' },
  { name: '소주', price: '4,000원' },
  { name: '생맥주', price: '4,000원' },
];

const Menu: React.FC = () => {
  return (
    <div className="menu-container">
      <h1 className="menu-title">마이리솔 메뉴</h1>
      <div className="menu-grid">
        {menuItems.map((item, index) => (
          <div className="menu-card" key={index}>
            <h2 className="menu-name">{item.name}</h2>
            <p className="menu-price">{item.price}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Menu;
