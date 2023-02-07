// @vitest-environment jsdom
import { render, screen, fireEvent } from '@testing-library/vue'
import { mount } from '@vue/test-utils'
import { test, describe, expect, it, vi, beforeEach } from 'vitest'
import TodoControls from '../components/TodoControls.vue'


describe('TodoInput', async () => {

    beforeEach(() => {
        vi.spyOn(window, 'fetch')
    })

    const wrapper = mount(TodoControls)


    it('Call backend on completed', async () => {
        const toggleButton = wrapper.find('[data-testid="completed-button"]')
    })

    
})
