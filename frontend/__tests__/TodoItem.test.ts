// @vitest-environment nuxt
import { fireEvent, render, screen } from '@testing-library/vue'
import { beforeEach, describe, expect, it, vi } from 'vitest'
import TodoItem from '~~/components/TodoItem.vue'

describe('TodoItem', async () => {

    beforeEach(() => {
        vi.spyOn(window, 'fetch')
    })

    const isCompleted = false
    const id = 'sampleID'
    render(TodoItem, {
        props: {
            title: 'Todo',
            id: id,
            isCompleted,
        },
    })

    it('render todo', async () => {
        const todo = await screen.findByText('Todo')
        expect(todo).toBeTruthy()
    })

    it('renders X button on hover', async () => {
        const deleteButton = await screen.getByLabelText('Delete')
        expect(deleteButton).toBeTruthy()
        const icon = screen.findByTestId('delete-icon')

        await fireEvent.mouseOver(deleteButton)

        expect(icon).toBeTruthy()

    })

    it('Call backend on removing todo', async () => {
        const deleteButton = await screen.getByLabelText('Delete')
        expect(deleteButton).toBeTruthy()

        await fireEvent.click(deleteButton)

        expect(fetch).toHaveBeenCalledWith(`http://localhost:8080/api/v1/todos/${id}`, {
            headers: {
                'Content-Type': 'application/json',
            },
            method: 'DELETE',
        })

    })

    it('Call backend when todo is completed', async () => {

        const toggleButton = await screen.getByLabelText('Toggle todo status')
        expect(toggleButton).toBeTruthy()

        await fireEvent.click(toggleButton)

        expect(fetch).toHaveBeenCalledWith(`http://localhost:8080/api/v1/todos/${id}`, {
            headers: {
                'Content-Type': 'application/json',
            },
            method: 'PATCH',
            body: JSON.stringify({
                "completed": !isCompleted
            }),
        })
    })

    it('Call backend when todo is edited', async () => {

        const todoButton = await screen.findByTestId('todo-button')
        expect(todoButton).toBeTruthy()

        await fireEvent.dblClick(todoButton)

        const input = await screen.getByTestId('todo-edit-input')
        const inputForm = await screen.getByTestId('todo-edit-form')
        expect(inputForm).toBeTruthy()
        expect(input).toBeTruthy()

        await fireEvent.update(input, 'New Todo')
        await fireEvent.submit(inputForm)

        expect(fetch).toHaveBeenCalledWith(`http://localhost:8080/api/v1/todos/${id}`, {
            headers: {
                'Content-Type': 'application/json',
            },
            method: 'PATCH',
            body: JSON.stringify({
                "title": 'New Todo'
            }),
        })
    })

    it('renders edit input when item is double clicked', async () => {
        const todoButton = await screen.findByTestId('todo-button')
        expect(todoButton).toBeTruthy()

        await fireEvent.dblClick(todoButton)

        const input = await screen.getByTestId('todo-edit-input')
        expect(input).toBeTruthy()
    })

    it('renders completed check when item is completed', async () => {
        render(TodoItem, {
            props: {
                title: 'Todo',
                id: id,
                isCompleted: true,
            }
        })
        screen.getByTestId('completed-icon')
    })

    it('renders not completed check when item is not completed', async () => {
        screen.getByTestId('incompleted-icon')
    })
})