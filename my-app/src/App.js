import React from "react";
import { BrowserRouter } from 'react-router-dom';
import LandingsPage from "./components/LandingsPage";
import Navbar from "./components/Navbar";

export default function App() {
  return (
    <BrowserRouter>
      <Navbar/>
      <LandingsPage/>
    </BrowserRouter>
  );
}
