import { defineStore } from 'pinia'
import { Todo } from '~/types/Todo'

export const useTodoStore = defineStore('todos', {

    state: () => ({
        todos: [] as Todo[],
    }),

    getters: {
        getActiveTodos(state) {
            return state.todos?.filter(todo => !todo.completed)
        },
        getCompletedTodos(state) {
            return state.todos?.filter(todo => todo.completed)
        },

        getActiveTodosCount(state) {
            return state.todos?.filter(todo => !todo.completed).length
        },
        getCompletedTodosAmount(state) {
            return state.todos?.filter(todo => todo.completed).length
        },
        getTodosAmount(state) {
            return state.todos?.length
        }


    },

    actions: {
        async fetchTodos() {
            const { data: todos, } = await useFetch<Todo[]>('http://localhost:8080/api/v1/todos')
            this.todos = todos as unknown as Todo[]
        },
        async createTodo() { },

        async setAllCompleted() {
            try {
                await fetch(`http://localhost:8080/api/v1/todos/toggle-all`, {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    method: 'PATCH',
                    body: JSON.stringify({
                    }),
                })
            } catch (e) {
                console.log('Error:' + e)
            }
            this.fetchTodos()
        },

        async addTodo(input: string) {
            try {
                await fetch(`http://localhost:8080/api/v1/todos`, {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    method: 'POST',
                    body: JSON.stringify({
                        "title": input,
                        "completed": false
                    }),
                })
            } catch (e) {
                console.log('Error:' + e)
            }
            this.fetchTodos()
        },

        async clearCompleted() {
            try {
                await fetch(`http://localhost:8080/api/v1/todos`, {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    method: 'DELETE',
                })
            } catch (e) {
                console.log('Error:' + e)
            }
            this.fetchTodos()
        },

        async toggleCompleted(id: string, isCompleted: boolean) {
            try {
                await fetch(`http://localhost:8080/api/v1/todos/${id}`, {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    method: 'PATCH',
                    body: JSON.stringify({
                        "completed": !isCompleted
                    }),
                })
            } catch (e) {
                console.log('Error:' + e)
            }
            this.fetchTodos()
        },

        async deleteTodoById(id: string) {
            try {
                await fetch(`http://localhost:8080/api/v1/todos/${id}`, {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    method: 'DELETE',
                })
            } catch (e) {
                console.log('Error:' + e)
            }
            this.fetchTodos()
        },

        async editTodoById(id: string, title: string) {
            try {
                await fetch(`http://localhost:8080/api/v1/todos/${id}`, {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    method: 'PATCH',
                    body: JSON.stringify({
                        "title": title
                    }),
                })
            } catch (e) {
                console.log('Error:' + e)
            }
            this.fetchTodos()
        },



    },




})