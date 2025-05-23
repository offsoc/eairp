/**
 * @description: Login interface parameters
 */
export interface LoginReq {
  username: string;
  password: string;
  captcha: string;
  captchaId: string;
}

export interface mobileLoginReq {
  phoneNumber: string;
  sms: string;
  type: number;
}

export interface emailLoginReq {
  email: string;
  emailCode: string;
  type: number;
}

export interface registerReq {
  username: string;
  password: string;
  email: string;
  emailCode: number;
}

export interface queryUserListReq {
  username: string;
  name: string;
}

export interface updatePasswordReq {
  password: string;
  phoneNumber: string;
  sms: number;
}

export interface updatePasswordByEmailReq {
  password: string;
  email: string;
  emailCode: number;
}

export interface resetPasswordReq {
  id: number | string;
  userName: string;
  password: string;
  newPassword: string;
}

export interface resetPhoneNumberReq {
  userId: number | string;
  oldPhoneNumber: string;
  phoneNumber: string;
  sms: string;
}

export interface resetEmailReq {
  userId: number | string;
  oldEmail: string;
  email: string;
  emailCode: string;
}

export interface updateUserInfoReq {
  id: number | string;
  name: string;
  status: number;
  email: string;
  phoneNumber: string;
  position: string;
  leaderFlag: number;
  systemLanguage: string;
}

export interface addOrUpdateUserReq {
  id: number | string;
  username: string;
  password: string;
  name: string;
  email: string;
  phoneNumber: string;
  roleId: number;
  deptId: number;
  remake: string;
}

export interface RoleInfo {
  roleName: string;
  value: string;
}

export interface LoginResp {
  userId: string | number;
  token: string;
  expire?: number;
}

/**
 * @description: Login interface return value
 */
export interface LoginResultModel {
  userId: string | number;
  token: string;
  roles: RoleInfo[];
}

/**
 * @description: Get user information return value
 */
export interface GetUserInfoModel {
  // 用户id
  id: string | number;
  // 用户名
  username: string;
  // 昵称
  name: string;
  // 邮箱
  email: string;
  // 电话
  phoneNumber: string;
  // 状态
  status: number;
  // 用户角色名称
  roleName: string;
  // 头像
  avatar: string;
  // 系统语言
  systemLanguage: string;
}
