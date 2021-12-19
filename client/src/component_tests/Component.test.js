import {render, screen} from '@testing-library/react';
import {Home} from '../components/home/Home';
import {Header} from "../components/header/Header";

test('Home page test', () => {
  const HOME_PAGE_TEXT = "Home page";
  render(<Home text={HOME_PAGE_TEXT}/>);
  const homeText = screen.queryByText(HOME_PAGE_TEXT);
  expect(homeText).toBeInTheDocument();
});

test('Header test', () => {
  render(<Header/>);
  const links = screen.queryAllByRole('link');
  expect(links).toHaveLength(2);
  expect(links[0]).toHaveTextContent("Home");
  expect(links[1]).toHaveTextContent("Todolist");
});


