// call api
export const login = (credential) => {
    const loginUrl = `/login?username=${credential.username}&password=${credential.password}`;
//   真正负责发起通信，浏览器提供的window global 函数
    return fetch(loginUrl, {
        //两个参数，第一个参数是url
      method: "POST",
      //request header
      headers: {
        "Content-Type": "application/json",
      },
      //告诉浏览器，response header时要接受cookie
      credentials: "include",
    //   命令浏览器发起http请求
    }).then((response) => {  //then是个函数，fetch的结果是个object，可以调用.then（）
        //then 目前take一个参数，该参数是一个回调函数，在发送请求，结果回来之后处理结果
      if (response.status < 200 || response.status >= 300) {
        throw Error("Fail to log in");
      }
    });
};

export const signup = (data) => {
  const signupUrl = "/signup";

  return fetch(signupUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  }).then((response) => {
    if (response.status < 200 || response.status >= 300) {
      throw Error("Fail to sign up");
    }
  });
};

export const getMenus = (restId) => {
  return fetch(`/restaurant/${restId}/menu`).then((response) => {
    if (response.status < 200 || response.status >= 300) {
      throw Error("Fail to get menus");
    }

    return response.json();
  });
};

export const getRestaurants = () => {
  return fetch("/restaurants").then((response) => {
    if (response.status < 200 || response.status >= 300) {
      throw Error("Fail to get restaurants");
    }

    return response.json();
  });
};

export const getCart = () => {
  return fetch("/cart").then((response) => {
    if (response.status < 200 || response.status >= 300) {
      throw Error("Fail to get shopping cart data");
    }

    return response.json();
  });
};

export const checkout = () => {
  return fetch("/checkout").then((response) => {
    if (response.status < 200 || response.status >= 300) {
      throw Error("Fail to checkout");
    }
  });
};

export const addItemToCart = (itemId) => {
  return fetch(`/order/${itemId}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    credentials: "include",
  }).then((response) => {
    if (response.status < 200 || response.status >= 300) {
      throw Error("Fail to add menu item to shopping cart");
    }
  });
};
