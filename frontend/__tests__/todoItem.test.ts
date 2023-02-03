// @vitest-environment nuxt
import { mount } from '@vue/test-utils'
import { test, expect} from 'vitest'
import TodoInput from '../components/TodoInput.vue'

// The component to test
const MessageComponent = {
  template: '<p>{{ msg }}</p>',
  props: ['msg']
}

test('displays message', () => {
  const wrapper = mount(TodoInput)

  // Assert the rendered text of the component

  expect(wrapper).toBeTruthy()
  
})