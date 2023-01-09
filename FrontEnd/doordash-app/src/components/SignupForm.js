import { Button, Form, Input, message, Modal } from "antd";
import React from "react";
import { LockOutlined, UserOutlined } from "@ant-design/icons";
import { signup } from "../utils";

class SignupForm extends React.Component {
  
    
  state = {
    displayModal: false,
    //本地state 控制
    loading: false,
  };

  handleCancel = () => {
    this.setState({
      displayModal: false,
    });
  };

  signupOnClick = () => {
    this.setState({
      displayModal: true,
    });
  };

  onFinish = (data) => {
    signup(data)
    .then(() => {
      this.setState({
        displayModal: false,
        loading: true,
      });
      message.success(`Successfully signed up`);
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
  return (
    <>
      <Button shape="round" type="primary" onClick={this.signupOnClick}>
        Register
      </Button>
      <Modal
        title="Register"
        visible={this.state.displayModal}
        // 测试是否弹开
        onCancel={this.handleCancel}
        footer={null}
        // 拒绝使用自带按钮
        destroyOnClose={true}
        // destory form里面的东西（unmounting）
      >
        <Form
          name="normal_register"
          initialValues={{ remember: true }}
          onFinish={this.onFinish}
          preserve={false}
        >
          <Form.Item
            name="email"
            rules={[{ required: true, message: "Please input your email!" }]}
            >
            <Input prefix={<UserOutlined />} placeholder="Email" />
          </Form.Item>
          <Form.Item
            name="password"
            rules={[
              { required: true, message: "Please input your password!" },
            ]}
          >
            <Input prefix={<LockOutlined />} placeholder="Password" />
          </Form.Item>
          <Form.Item
            name="firstName"
            rules={[
              { required: true, message: "Please input your first name!" },
            ]}
          >
            <Input placeholder="firstname" />
          </Form.Item>
          <Form.Item
            name="lastName"
            rules={[
              { required: true, message: "Please input your last name!" },
            ]}
          >
            <Input placeholder="lastname" />
          </Form.Item>

          <Form.Item>
            <Button type="primary" htmlType="submit" loading={this.state.loading}>
              Register
            </Button>
          </Form.Item>
         
        </Form>
        </Modal>
      </>
    );
  };
}

export default SignupForm;


