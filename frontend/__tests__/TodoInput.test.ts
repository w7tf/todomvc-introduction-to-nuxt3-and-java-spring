// @vitest-environment nuxt
import { render, screen, fireEvent } from '@testing-library/vue'
import { test, describe, expect, it, vi, beforeEach } from 'vitest'
import TodoInput from '~~/components/TodoInput.vue'


describe('TodoInput', async () => {

    beforeEach(() => {
        vi.spyOn(window, 'fetch')
    })

    render(TodoInput)

    

    it('Call backend on completed toggle', async () => {
        const toggleButton = await screen.findByTestId('completed-button')
        expect(toggleButton).toBeTruthy()

        await fireEvent.click(toggleButton)

        expect(fetch).toHaveBeenCalledWith(`http://localhost:8080/api/v1/todos/toggle-all`, {
            headers: {
                'Content-Type': 'application/json',
            },
            method: 'PATCH',
            body: JSON.stringify({
            }),
        })
    })


    it('Call backend on adding todo', async () => {
        const input = await screen.findByTestId('todo-input')
        expect(input).toBeTruthy()

        await fireEvent.update(input, 'Todo')

        const form = await screen.findByTestId('todo-form')
        expect(form).toBeTruthy()

        await fireEvent.submit(form)

        expect(fetch).toHaveBeenCalledWith(`http://localhost:8080/api/v1/todos`, {
            headers: {
                'Content-Type': 'application/json',
            },
            method: 'POST',
            body: JSON.stringify({
                "title": 'Todo',
                "completed": false
            }),
        })
    })

})
