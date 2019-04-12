const getters = {
  userInfo: state => state.user.userInfo,
  plateNumber: state => state.billQuery.plateNumber,
  prePay: state => state.billQuery.prePay,
  returnURL: state => state.billQuery.returnURL,
  appId: state => state.user.appId
}
export default getters
