/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      fontFamily: {
        sans: ['Roboto', 'sans-serif'],
      },
      colors: {
        'main_blue': '#1B60FF',
        'gradient_start': '#001233',
        'gradient_mid': '#5676A7',
        'gradient_end': '#7EA4DE',
      }
    },
  },
  plugins: [],
}