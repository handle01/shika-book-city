<template>
  <!--修改-->
  <el-dialog
    v-model="dialogVisible"
    title="书籍信息"
    width="50%"
    :before-close="handleClose"
    modal-append-to-body="false"
    ><div class="mycontainer">
      <el-form
        ref="ruleFormRef"
        :model="form"
        label-width="100px"
        :rules="rules"
      >
        <el-form-item
          label="书籍姓名"
          prop="name"
          :rules="[
            {
              required: true,
              message: '不能为空',
              trigger: 'blur',
            },
          ]"
        >
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-row>
          <el-form-item
            prop="isbn"
            label="ISBN"
            :rules="[
              {
                required: true,
                message: '不能为空',
                trigger: 'blur',
              },
              {
                max: 13,
                min: 13,
                message: 'ISBN必须为13位',
              },
            ]"
          >
            <el-input v-model="form.isbn"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item
            prop="brand"
            label="品牌"
            :rules="[
              {
                required: true,
                message: '不能为空',
                trigger: 'blur',
              },
            ]"
          >
            <el-input v-model="form.brand"></el-input>
          </el-form-item>
          <el-form-item
            prop="price"
            label="价格"
            :rules="[
              {
                required: true,
                message: '不能为空',
                trigger: 'blur',
              },
              {
                type: 'number',
                message: '必须为数字',
                trigger: 'blur',
              },
            ]"
          >
            <el-input v-model.number="form.price"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item
            prop="author"
            label="作者"
            :rules="[
              {
                required: true,
                message: '不能为空',
                trigger: 'blur',
              },
            ]"
          >
            <el-input v-model="form.author"></el-input>
          </el-form-item>
          <el-form-item
            prop="press"
            label="出版社"
            :rules="[
              {
                required: true,
                message: '不能为空',
                trigger: 'blur',
              },
            ]"
          >
            <el-input v-model="form.press"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item
            pror="thePaper"
            label="用纸类型"
            :rules="[
              {
                required: true,
                message: '不能为空',
                trigger: 'blur',
              },
            ]"
          >
            <el-select v-model="form.thePaper" popper-class="z">
              <el-option label="纯质纸" value="纯质纸" />
              <el-option label="胶版纸" value="胶版纸" />
              <el-option label="轻型纸" value="轻型纸" />
              <el-option label="超感纸" value="超感纸" />
              <el-option label="铜版纸" value="铜版纸" />
              <el-option label="特种纸" value="特种纸" />
              <el-option label="灰纸板" value="灰纸板" />
              <el-option label="轻质微涂纸" value="轻质微涂纸" />
            </el-select>
          </el-form-item>
          <el-form-item
            pror="packaging"
            label="包装类型"
            :rules="[
              {
                required: true,
                message: '不能为空',
                trigger: 'blur',
              },
            ]"
            ><el-select v-model="form.packaging" popper-class="z">
              <el-option label="精装" value="精装" />
              <el-option label="平装" value="平装" />
              <el-option label="线装" value="线装" />
              <el-option label="骑马订装" value="骑马订装" />
              <el-option label="散页装" value="散页装" />
            </el-select>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item
            pror="format"
            label="开本类型"
            :rules="[
              {
                required: true,
                message: '不能为空',
                trigger: 'blur',
              },
            ]"
            ><el-select v-model="form.format" popper-class="z">
              <el-option label="16开" value="16开" />
              <el-option label="32开" value="32开" />
            </el-select>
          </el-form-item>
          <el-form-item pror="publicationTime" label="出版时间">
            <div class="demo-date-picker">
              <el-date-picker
                v-model="form.publicationTime"
                type="date"
                placeholder="     出版时间"
                format="YYYY/MM/DD"
                value-format="YYYY-MM-DD"
                popper-class="z"
              >
                <template #default="cell">
                  <div class="cell" :class="{ current: cell.isCurrent }">
                    <span class="text">{{ cell.text }}</span>
                    <span v-if="isHoliday(cell)" class="holiday" />
                  </div>
                </template>
              </el-date-picker>
            </div>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item
            pror="imageUrl"
            label="封面"
            :rules="[
              {
                required: true,
                message: '不能为空',
                trigger: 'blur',
              },
            ]"
          >
            <el-upload
              class="avatar-uploader"
              action="/user/upload"
              :auto-upload="autoUpload"
              name="imgFile"
              :headers="headers"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
            >
              <img v-if="imageUrl" :src="imageUrl" class="avatar" />
              <el-icon v-else class="avatar-uploader-icon"
                ><i class="el-icon"
                  ><svg
                    viewBox="0 0 1024 1024"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      fill="currentColor"
                      d="M480 480V128a32 32 0 0 1 64 0v352h352a32 32 0 1 1 0 64H544v352a32 32 0 1 1-64 0V544H128a32 32 0 0 1 0-64h352z"
                    ></path></svg></i
              ></el-icon>
            </el-upload>
          </el-form-item>
          <el-form-item pror="description" label="简介"
            ><el-input type="textarea" v-model="form.description"></el-input>
          </el-form-item>
        </el-row>
        <el-form-item label="类型">
          <el-checkbox-group v-model="form.type" :max="3">
            <el-checkbox v-for="city in cities" :key="city" :label="city">{{
              city
            }}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="valid(form)">修改</el-button>
          <el-button @click="dialogVisible = false">退出</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-dialog>

  <el-table :data="filterTableData" style="width: 100%">
    <el-table-column label="封面">
      <template #default="scope">
        <img :src="scope.row.cover" style="height: 50px" />
      </template>
    </el-table-column>

    <el-table-column label="书名" prop="name" />
    <el-table-column label="品牌" prop="brand" />
    <el-table-column label="作者" prop="author" />
    <el-table-column label="出版社" prop="press" />
    <el-table-column label="价格（单位：元）" prop="price" />
    <el-table-column label="类型" prop="type" />

    <el-table-column align="right">
      <template #header>
        <el-input v-model="search" size="small" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <el-button size="small" @click="handleEdit(scope.row)">修改</el-button>
        <el-button size="small" type="danger" @click="added(scope.row.id)"
          >下架</el-button
        >
        <el-button size="small" type="danger" @click="Delete(scope.row.id)"
          >删除</el-button
        >
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
    small
    background
    layout="prev, pager, next"
    :total="total"
    class="mt-4"
    :page-size="pageSize"
    @current-change="changPage"
  />
</template>
<script  setup>
import { ElMessage, ElMessageBox } from "element-plus";
import { computed, ref } from "vue";
import { getIsAdded, submit, del, noAdded } from "@/api/book";
import { onMounted } from "vue";
//上传文件携带请求头认证
let headers = ref({
  token: storage.getSessionString("token"),
  userName: storage.getSessionString("userName"),
});
// const value = ref('')
const holidays = [
  "2021-10-01",
  "2021-10-02",
  "2021-10-03",
  "2021-10-04",
  "2021-10-05",
  "2021-10-06",
  "2021-10-07",
];
const isHoliday = ({ dayjs }) => {
  return holidays.includes(dayjs.format("YYYY-MM-DD"));
};
//复选框数据

const cities = [
  "言情",
  "穿越/重生",
  "武侠",
  "惊悚/恐怖",
  "玄幻/奇幻",
  "历史",
  "官场",
  "社会",
  "军事",
  "外国小说",
  "中国当代小说",
  "中国古典小说",
  "侦探/悬疑/推理",
];

//是否显现
var dialogVisible = ref();
const ruleFormRef = ref(null);
var addForm = ref({}); //新增表单
var form = ref({}); //表单
var tableData = ref([]); //列表数据
var total = ref(0); //数据总条数
var pageSize = ref(0); //页面最大展现条数
let imageUrl = ref(); //封面图片
import storage from "@/util/storage";

let get = ref({
  size: 10,
  current: 1,
  userName: storage.getSessionString("userName"),
});
//页面跳转渲染数据
onMounted(() => {
  console.log("---------------------------------")
  getIsAdded(get.value).then((res) => {
    tableData.value = res.data.data.list;
    pageSize.value = res.data.data.pageSize;
    total.value = res.data.data.total;
    console.log(res.data.data.value)
    console.log(tableData.value)
    console.log(pageSize.value)
    console.log(total.value)
  });
});
//上传完成后执行
let handleAvatarSuccess = function (response) {
  imageUrl.value = response.data;
  form.value.cover = response.data;
  addForm.value.cover = response.data;
  ElMessage({
    showClose: true,
    message: response.message,
    type: "success",
  });
};
//上传前校验
let beforeAvatarUpload = function (rawFile) {
  const isJPG = rawFile.type === "image/jpeg";
  const isLt2M = rawFile.size / 1024 / 1024 < 2;
  if (!isJPG) {
    ElMessage({
      showClose: true,
      message: "上传头像图片只能是 JPG 格式!",
      type: "error",
    });
  }
  if (!isLt2M) {
    ElMessage({
      showClose: true,
      message: "上传头像图片大小不能超过 2MB!",
      type: "error",
    });
  }
  return isJPG && isLt2M;
};

let currentPage = 1;
//切换页
let changPage = function (page) {
  currentPage = page;
  getIsAdded({
    current: page,
    size: 10,
    userName: storage.getSessionString("userName"),
  }).then((res) => {
    tableData.value = res.data.data.list;
  });
};
// const list = tableData.value;

let search = ref("");
//搜索
let filterTableData = computed(() =>
  tableData.value.filter(
    (data) =>
      !search.value ||
      data.name.toLowerCase().includes(search.value.toLowerCase())
  )
);
//修改
const handleEdit = (from) => {
  form.value = from;
  imageUrl.value = from.cover;
  dialogVisible.value = !dialogVisible.value;
};

//删除
const Delete = (data) => {
  ElMessageBox.confirm("是否要删除该书籍?", "提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      var id = {
        bookId: data,
        userId: storage.getSessionString("id"),
      };
      del(id).then((res) => {
        if (res.data.flag === true) {
          ElMessage.success(res.data.message);
          getIsAdded({
            size: 10,
            current: currentPage,
            userName: storage.getSessionString("userName"),
          }).then((res) => {
            tableData.value = res.data.data.list;
            pageSize.value = res.data.data.pageSize;
            total.value = res.data.data.total;
          });
        } else {
          ElMessage.error(res.data.message);
        }
      });
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "取消成功",
      });
    });
};

//下架
const added = (data) => {
  ElMessageBox.confirm("是否要上架该书籍?", "提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      var id = {
        id: data,
      };
      noAdded(id).then((res) => {
        if (res.data.flag === true) {
          ElMessage({
            showClose: true,
            message: res.data.message,
            type: "success",
          });
          getIsAdded({
            size: 10,
            current: currentPage,
            userName: storage.getSessionString("userName"),
          }).then((res) => {
            tableData.value = res.data.data.list;
            pageSize.value = res.data.data.pageSize;
            total.value = res.data.data.total;
          });
        } else {
          ElMessage({
            showClose: true,
            message: res.data.message,
            type: "error",
          });
        }
      });
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "取消成功",
      });
    });
};
//提交表单
const valid = (form) => {
  // !Number.isInteger(value)
  ruleFormRef.value.validate((valid) => {
    if (valid) {
      submit(form).then((res) => {
        if (res.data.flag === true) {
          ElMessage({
            showClose: true,
            message: res.data.message,
            type: "success",
          });
          dialogVisible.value = !dialogVisible.value;
          getIsAdded({
            size: 10,
            current: currentPage,
            userName: storage.getSessionString("userName"),
          }).then((res) => {
            tableData.value = res.data.data.list;
            pageSize.value = res.data.data.pageSize;
            total.value = res.data.data.total;
          });
        } else {
          ElMessage({
            showClose: true,
            message: res.data.message,
            type: "error",
          });
        }
      });
    } else {
      ElMessage.error("表单信息不全");
      return false;
    }
  });
};
</script>
<style >
.z {
  z-index: 10001 !important;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.el-input__inner {
  -webkit-appearance: none;
  background-color: var(--el-input-bg-color, var(--el-fill-color-blank));
  background-image: none;
  border-radius: var(--el-input-border-radius, var(--el-border-radius-base));
  box-sizing: border-box;
  color: var(--el-input-text-color, var(--el-text-color-regular));
  display: inline-block;
  font-size: inherit;
  height: 32px;
  line-height: 32px;
  outline: 0;
  padding: 0 11px;
  transition: var(--el-transition-box-shadow);
  width: 500%;
  box-shadow: 0 0 0 1px var(--el-input-border-color, var(--el-border-color))
    inset;
  border: none;
}
.avatar-uploader .el-upload {
  width: 120px;
  height: 120px;
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}
</style>

