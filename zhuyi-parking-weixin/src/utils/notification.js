import { Notification } from 'element-ui'
export default function msg (type, title, message, position, duration) {
  Notification({
    title: title || '警告',
    message: message || '信息不完整!',
    position: position || 'top-left',
    type: type || 'warning',
    duration: duration || 3500
  })
}
