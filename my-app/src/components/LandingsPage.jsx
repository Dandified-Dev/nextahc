import React from 'react';
import Chat from '../assets/chat.png';

const LandingsPage = () => {
  return (
    <div className="min-h-screen flex text-white mt-20 lg:-mt-40 justify-center lg:mx-40 lg:items-center lg:justify-around">
      <div className='flex-col h-[30vh] justify-evenly hidden lg:flex'>
        <p className='lg:text-8xl w-2/3'>Chat with</p>
        <p className='lg:text-8xl'>your friends and family!</p>
        <p className='lg:text-lg'>#1 Way To Connect With People</p>
        <p className='lg:text-xl px-7 py-4 bg-main_blue w-40 rounded-lg justify-center text-center cursor-pointer'>Get Started</p>
      </div>
      <div className='justify-center hidden lg:block'>
        <img src={Chat} alt="" />
      </div>
      <div>
      <div className='justify-center lg:hidden'>
        <img className='h-[30vh]' src={Chat} alt="" />
      </div>
      <div className='flex-col h-[30vh] justify-evenly lg:hidden mt-[10vh]'>
        <p className='w-2/3'>Chat with</p>
        <p>your friends and family!</p>
        <p>#1 Way To Connect With People</p>
        <p className=' px-7 py-4 bg-main_blue w-40 rounded-lg justify-center text-center cursor-pointer'>Get Started</p>
      </div>

      </div>
    </div>
  );
};

export default LandingsPage;
