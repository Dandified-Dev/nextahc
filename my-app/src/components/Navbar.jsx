import React, { useState } from 'react';
import logo from "../assets/logo2.png";
import { AiOutlineClose, AiOutlineMenu } from 'react-icons/ai';


function Navbar() {
  const [nav, setNav] = useState(false);

  const handleNav = () => {
    setNav(!nav);
  };

  return (
    <div className="w-full mt-8">
      <div className="flex text-white justify-between md:mx-8 xl:mx-20 items-center md:text-2xl font-medium mx-5">
        <img className="w-[20vh] relative cursor-pointer" src={logo} alt="" />
        <ul className="justify-between items-center w-[55vh] 2xl:mr-[12vh] hidden md:flex">
          <li className="cursor-pointer">HOME</li>
          <li className="cursor-pointer">ABOUT</li>
          <li className="cursor-pointer">CONTACT</li>
          <li className="text-[#031F47] bg-[#9db5d9] py-4 px-[5vh] rounded-md text-w cursor-pointer font-bold">
            LOGIN
          </li>
        </ul>
        <div onClick={handleNav} className='block md:hidden'>
          {nav ? <AiOutlineClose size={20}/> : <AiOutlineMenu size={20} />}
      </div>
        <ul className={nav ? 'fixed left-0 top-0 w-[50%] h-full border-r border-r-gray-900 bg-gray-900 ease-in-out duration-500' : 'ease-in-out duration-500 fixed left-[-100%]'}>
        <h1 className='w-full text-3xl font-bold text-main_blue m-4'>NEXTAHC</h1>
          <li className='p-4 border-b border-gray-600'>Login/signUp</li>
          <li className='p-4 border-b border-gray-600'>Home</li>
          <li className='p-4 border-b border-gray-600'>About</li>
          <li className='p-4 border-b border-gray-600'>Contact</li>
      </ul>
      </div>
      {/* Your navbar content goes here */}
    </div>
  );
}

export default Navbar;
