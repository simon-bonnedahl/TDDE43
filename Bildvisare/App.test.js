import React from 'react';
import renderer from 'react-test-renderer';
import {render, screen, fireEvent} from '@testing-library/react-native'

import App from './App';

describe('<App />', () => {
    
    it('renders correctly', () => {
        const tree = renderer.create(<App />).toJSON();
        expect(tree).toMatchSnapshot();
      });
    
    it('refresh button works', async () => {
        render(<App/>)
        const image = await screen.findByTestId('image')
        const uriBefore = image.props['source'].uri
        await fireEvent.press(screen.getByTestId('refresh-button'))
        const uriAfter = image.props['source'].uri
        expect(uriAfter).toEqual(expect.not.stringMatching(uriBefore))
        
    })
    it('plus button works', async ()=>  {
        render(<App/>)
        const image = await screen.findByTestId('image')
        const sizeBefore = image.props['source'].width
        await fireEvent.press(screen.getByTestId('plus-button'))
        const sizeAfter = image.props['source'].width
        expect(sizeAfter).toBe(sizeBefore+10)

    })
    it('minus button works', async ()=>{
        render(<App/>)
        const image = await screen.findByTestId('image')
        const sizeBefore = image.props['source'].width
        await fireEvent.press(screen.getByTestId('minus-button'))
        const sizeAfter = image.props['source'].width
        expect(sizeAfter).toBe(sizeBefore-10)
    })
    it('rotate-left button works', async ()=>{
        render(<App/>)
        const image = await screen.findByTestId('image')
        const rotationBefore = image.props['source'].rotate
        await fireEvent.press(screen.getByTestId('rotate-left-button'))
        const rotationAfter = image.props['source'].rotate
        expect(rotationAfter).toBe(rotationBefore-90)
    })
    it('rotate-right button works', async ()=>{
        render(<App/>)
        const image = await screen.findByTestId('image')
        const rotationBefore = image.props['source'].rotate
        await fireEvent.press(screen.getByTestId('rotate-right-button'))
        const rotationAfter = image.props['source'].rotate
        expect(rotationAfter).toBe(rotationBefore+90)
    })

    
});

