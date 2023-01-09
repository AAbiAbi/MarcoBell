import { Button, Form, Input, message } from "antd";
import React from "react";
import { LockOutlined, UserOutlined } from "@ant-design/icons";
import { login } from "../utils";
// 用的class，比较老
// login form是比较独立的
class LoginForm extends React.Component {
  // 
  state = {
    // 一个state
    loading: false,
    // 初始值
  };

  onFinish = (data) => {
    // 自定义函数
    // 表格里用户填的东西object，浏览器
    this.setState({
      loading: true,
    // button
    });
    login(data)
      .then(() => {
        message.success(`Login Successful`);
        this.props.onSuccess();
      })
      .catch((err) => {
        message.error(err.message);
      })
      .finally(() => {
        this.setState({
          loading: false,
          // 无论成功失败，都要flip button
        });
      });
  };

  render = () => {
    // 跟UI紧密结合
    return (
        // 最底层
      <Form
        name="normal_login"
        onFinish={this.onFinish}
        // 点击提交按钮的时候
        style={{
          width: 300,
          margin: "auto",
        //   居中
        }}
      >
        <Form.Item
          name="username"
        //   收集data时的property
          rules={[{ required: true, message: "Please input your Username!" }]}
        >
          <Input prefix={<UserOutlined />} placeholder="Username" />
        </Form.Item>
        <Form.Item
          name="password"
          rules={[{ required: true, message: "Please input your Password!" }]}
        >
          <Input.Password prefix={<LockOutlined />} placeholder="Password" />
        </Form.Item>

        <Form.Item>
          <Button type="primary" htmlType="submit" loading={this.state.loading}>
            {/* 防止用户狂点 ，用户点按钮的时候要call api，调用api时可以block*/}
            Login
          </Button>
          
        </Form.Item>
      </Form>
    );
  };
}

export default LoginForm;

