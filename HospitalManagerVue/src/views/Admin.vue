<template>
  <el-container>
    <!-- 头部 -->
    <el-header>
      <div class="words">
        <span @click="menuClick('adminLayout')">
          <i class="iconfont icon-r-love" style="font-size: 26px;"> 医院管理系统</i>
        </span>
      </div>
      <div class="words">
        <span>欢迎您，<b>{{ userName }}</b>&nbsp;管理员&nbsp;</span>
        <span><el-button type="danger" size="mini" @click="logout">退出登录</el-button></span>
      </div>
    </el-header>
    <el-container>
      <!-- 侧边栏 -->
      <el-aside width="200px">
        <!-- 导航菜单 -->
        <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
          <el-radio-button :label="false">展开</el-radio-button>
          <el-radio-button :label="true">收起</el-radio-button>
        </el-radio-group>
        <el-menu default-active="1-4-1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
          :collapse="isCollapse">
          <el-menu-item index="adminLayout" @click="menuClick('adminLayout')">
            <i class="iconfont icon-r-home" style="font-size: 22px;"></i>
            <span slot="title" style="font-size: 22px;">首页</span>
          </el-menu-item>
          <el-menu-item index="doctorList" @click="menuClick('doctorList')">
            <i class="iconfont icon-r-user1" style="font-size: 22px;"></i> <span slot="title"
              style="font-size: 22px;">医生信息管理</span>
          </el-menu-item>
          <el-menu-item index="patientList" @click="menuClick('patientList')">
            <i class="iconfont icon-r-user2" style="font-size: 22px;"></i> <span slot="title"
              style="font-size: 22px;">患者信息管理</span>
          </el-menu-item>
          <el-menu-item index="orderList" @click="menuClick('orderList')">
            <i class="iconfont icon-r-paper" style="font-size: 22px;"></i> <span slot="title"
              style="font-size: 22px;">挂号信息管理</span>
          </el-menu-item>
          <el-menu-item index="drugList" @click="menuClick('drugList')">
            <i class="iconfont icon-r-love" style="font-size: 22px;"></i> <span slot="title"
              style="font-size: 22px;">药物信息管理</span>
          </el-menu-item>
          <el-menu-item index="checkList" @click="menuClick('checkList')">
            <i class="iconfont icon-r-edit" style="font-size: 22px;"></i> <span slot="title"
              style="font-size: 22px;">检查项目管理</span>
          </el-menu-item>
          <el-menu-item index="bedList" @click="menuClick('bedList')">
            <i class="iconfont icon-r-list" style="font-size: 22px;"></i> <span slot="title"
              style="font-size: 22px;">病床信息管理</span>
          </el-menu-item>
          <el-menu-item index="arrangeIndex" @click="menuClick('arrangeIndex')">
            <i class="iconfont icon-r-shield" style="font-size: 22px;"></i> <span slot="title"
              style="font-size: 22px;">排班信息管理</span>
          </el-menu-item>
          <el-menu-item index="dataExpore" @click="menuClick('dataExpore')">
            <i class="iconfont icon-r-mark1" style="font-size: 22px;"></i>
            <span slot="title" style="font-size: 22px;">数据统计分析</span>
          </el-menu-item>

        </el-menu>
      </el-aside>
      <el-main>
        <!-- 子路由入口 -->
        <router-view>
          <div>你好吗</div>
        </router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
import jwtDecode from "jwt-decode";
import { getToken, clearToken, getActivePath, setActivePath } from "@/utils/storage.js";
export default {
  name: "Admin",
  data() {
    return {
      userName: "",
      activePath: "",
      isCollapse: true,
    };
  },
  methods: {
    //token解码
    tokenDecode(token) {
      if (token !== null)
        return jwtDecode(token);
    },
    //导航栏点击事件
    menuClick(path) {
      this.activePath = path;
      setActivePath(path);
      if (this.$route.path !== "/" + path) this.$router.push(path);
      console.log(path)
    },
    //退出登录
    logout() {
      this.$confirm("此操作将退出登录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          clearToken();
          this.$message({
            type: "success",
            message: "退出登录成功!",
          });
          this.$router.push("login");
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消",
          });
        });
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  },
  created() {
    //  获取激活路径
    this.activePath = getActivePath();
    // 解码token
    this.userName = this.tokenDecode(getToken()).aName;

  }
};
</script>

<style scoped lang="scss">
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.title {
  cursor: pointer;
}

.el-header {
  background-color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;

  .words {
    text-align: center;

    span {
      color: black;
    }
  }

  border-bottom: 1px solid lightgrey;
}

.el-container {
  height: 100%;
}

// .el-aside {
//   background-color: white;
//   border-right: 1px solid lightgrey;
// }

// .el-menu {
//   border: 0;
// }
</style>
